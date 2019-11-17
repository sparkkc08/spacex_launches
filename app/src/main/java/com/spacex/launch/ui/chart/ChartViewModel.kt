package com.spacex.launch.ui.chart

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.spacex.launch.data.launches.LaunchesRepository
import com.spacex.launch.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val CHART_LABEL_FORMAT = "MM/yy";

class ChartViewModel @Inject constructor(launchesRepository: LaunchesRepository) : BaseViewModel() {

    val chartData = ObservableField<List<Pair<String, Int>>>()
    val progressBarVisibility = ObservableInt(View.GONE)

    private val launchDateFormat = SimpleDateFormat(CHART_LABEL_FORMAT, Locale.US)

    init {
        addSubscription(launchesRepository.getPastLaunches()
            .map { launches ->
                launches.filter { !it.upcoming }.groupBy(
                    { value -> launchDateFormat.format(value.launchDateLocal) },
                    { item -> item.launchSuccess })
            }
            .map { mapLaunches ->
                mapLaunches.map {
                    it.key to it.value.asSequence().count()
                }.filter { it.second != 0 }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressBarVisibility.set(View.VISIBLE) }
            .doOnSuccess { progressBarVisibility.set(View.GONE) }
            .subscribe({
                chartData.set(it)
            }, { e ->
                e.printStackTrace()
            })
        )
    }
}