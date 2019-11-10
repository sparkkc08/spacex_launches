package com.spacex.launch.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spacex.launch.BuildConfig
import com.spacex.launch.api.SpaceXApi
import com.spacex.launch.utils.API_DATE_FORMAT
import com.spacex.launch.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @JvmStatic
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat(API_DATE_FORMAT)
        .create()

    @Provides
    @Singleton
    @JvmStatic
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideSpaceXApi(retrofit: Retrofit): SpaceXApi {
        return retrofit.create(SpaceXApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRetrofitInterface(
        gsonConverterFactory: GsonConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}
