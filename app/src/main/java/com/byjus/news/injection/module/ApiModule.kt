package com.byjus.news.injection.module

import com.byjus.news.data.NewsRestServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    internal fun provideNewsApi(retrofit: Retrofit): NewsRestServiceApi =
            retrofit.create(NewsRestServiceApi::class.java)
}