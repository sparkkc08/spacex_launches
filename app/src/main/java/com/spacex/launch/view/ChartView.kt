package com.spacex.launch.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.contains
import com.spacex.launch.R
import kotlin.math.min

private const val LINE_WIDTH = 1f
private const val ROW_DEFAULT_STEP = 1
private const val ROW_DEFAULT_HEIGHT = 150
private const val COLUMN_WIDTH_MULTIPLIER = 0.5f
private const val BORDER = 40f
private const val TEXT_SIZE = 40f
private const val ROW_NUMBER = 5

class ChartView : View {
    private val textBounds = Rect()
    private val paint: Paint by lazyOf(Paint())
    private var values: MutableList<Pair<Int, RectF>> = mutableListOf()
    private var xLabels: MutableList<String> = mutableListOf()
    private var yLabels: MutableList<Int> = mutableListOf()
    private var title: String? = null
    private var maxValue: Int = 0
    private var minValue: Int = 0
    private var indexToHighlight: Int? = null
    private var rowStep: Int = ROW_DEFAULT_STEP
    private var valueColor = Color.BLUE

    private var columnWidth: Int = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet? = null, defStyle: Int = 0) {
        attrs?.run {
            val a = context.obtainStyledAttributes(this, R.styleable.ChartView, defStyle, 0)

            title = a.getString(R.styleable.ChartView_titleText)
            valueColor = a.getInt(R.styleable.ChartView_valueColor, Color.BLUE)

            a.recycle();
        }

        paint.strokeWidth = LINE_WIDTH
        paint.textSize = TEXT_SIZE
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_UP -> {
                val pointF = PointF(event.x, event.y)
                var clickedIndex: Int? = null
                values.forEachIndexed { index, pair ->
                    val (value, rectF) = pair
                    if (rectF.contains(pointF)) {
                        clickedIndex = index
                        return@forEachIndexed
                    }
                }

                if (clickedIndex != indexToHighlight) {
                    indexToHighlight = clickedIndex
                    invalidate()
                }

                return true
            }
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            else -> {
                super.onTouchEvent(event)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val requestedWidth = MeasureSpec.getSize(widthMeasureSpec)
        val requestedWidthMode = MeasureSpec.getMode(widthMeasureSpec)

        val requestedHeight = MeasureSpec.getSize(heightMeasureSpec)
        val requestedHeightMode = MeasureSpec.getMode(heightMeasureSpec)

        val desiredWidth: Int =
            (BORDER * 2 + xLabels.size * columnWidth * (1 + COLUMN_WIDTH_MULTIPLIER)).toInt()
        val desiredHeight: Int =
            (BORDER + ROW_NUMBER * ROW_DEFAULT_HEIGHT).toInt()

        val width = when (requestedWidthMode) {
            MeasureSpec.EXACTLY -> requestedWidth
            MeasureSpec.UNSPECIFIED -> desiredWidth
            else -> min(requestedWidth, desiredWidth)
        }

        val height = when (requestedHeightMode) {
            MeasureSpec.EXACTLY -> requestedHeight
            MeasureSpec.UNSPECIFIED -> desiredHeight
            else -> min(requestedHeight, desiredHeight)
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val startX = BORDER * 2
        val graphHeight = height - startX
        paint.color = Color.LTGRAY

        title?.let {
            drawTextCentred(canvas, paint, it, (startX + width) / 2, BORDER / 2)
        }

        yLabels.forEachIndexed { i, label ->
            val y = graphHeight / yLabels.size * (yLabels.size - i - 1) + BORDER
            canvas.drawLine(startX, y, width.toFloat(), y, paint)
            drawTextCentred(canvas, paint, label.toString(), startX / 2, y)
        }

        val xColWidth = columnWidth * (1 + COLUMN_WIDTH_MULTIPLIER)

        xLabels.forEachIndexed { i, label ->
            val x = xColWidth * i + startX
            canvas.drawLine(x, height - BORDER, x, BORDER, paint)
            drawTextCentred(
                canvas,
                paint,
                label,
                x + xColWidth / 2,
                height - BORDER / 2
            )
        }

        if (maxValue != minValue) {
            paint.color = valueColor
            values.forEachIndexed { i, pair ->
                val pxPerOne = graphHeight / (ROW_NUMBER * rowStep)
                val (value, rectF) = pair
                rectF.set(
                    (i * xColWidth) + startX,
                    graphHeight + BORDER - pxPerOne * value,
                    ((i * xColWidth) + startX) + (xColWidth - LINE_WIDTH),
                    graphHeight + BORDER
                )
                canvas.drawRect(rectF, paint)

                indexToHighlight.takeIf { i == it }?.let {
                    drawTextAbove(
                        canvas,
                        paint,
                        value.toString(),
                        rectF.left + rectF.width() / 2,
                        rectF.top
                    )
                }
            }
        }
    }

    private fun findFactor(value: Int): Int {
        return if (value < ROW_NUMBER) {
            ROW_DEFAULT_STEP
        } else {
            (2..value).firstOrNull { value % it == 0 && it * ROW_NUMBER > value }
                ?: ROW_DEFAULT_STEP
        }
    }

    private fun drawTextCentred(canvas: Canvas, paint: Paint, text: String, cx: Float, cy: Float) {
        paint.getTextBounds(text, 0, text.length, textBounds)
        drawText(
            canvas,
            paint,
            text,
            cx - textBounds.exactCenterX(),
            cy - textBounds.exactCenterY()
        )
    }

    private fun drawTextAbove(canvas: Canvas, paint: Paint, text: String, cx: Float, by: Float) {
        paint.getTextBounds(text, 0, text.length, textBounds)
        drawText(canvas, paint, text, cx - textBounds.exactCenterX(), by - textBounds.height())
    }

    private fun drawText(canvas: Canvas, paint: Paint, text: String, x: Float, y: Float) {
        val color = paint.color
        paint.color = Color.BLACK
        canvas.drawText(text, x, y, paint)
        paint.color = color
    }

    private fun getMaxTextSize(): Int {
        return xLabels.asSequence().map {
            paint.getTextBounds(it, 0, it.length, textBounds)
            textBounds.width()
        }.max() ?: 0
    }

    fun getHighlightedIndex(): Int? {
        return indexToHighlight
    }

    fun setHighlightedIndex(index: Int?) {
        if (indexToHighlight != index) {
            indexToHighlight = index
            invalidate()
        }
    }

    fun setValues(data: List<Pair<String, Int>>) {
        data.forEach {
            val (label, value) = it
            xLabels.add(label)
            values.add(Pair(value, RectF()))
        }

        maxValue = values.asSequence().map { it.first }.max() ?: 0
        minValue = values.asSequence().map { it.first }.min() ?: 0
        rowStep = findFactor(maxValue)

        yLabels = MutableList(ROW_NUMBER) { it.inc() * rowStep }

        columnWidth = getMaxTextSize()

        requestLayout()
    }
}