package com.byjus.news.injection.module

import android.app.Application
import android.content.Context
import com.byjus.news.injection.ApplicationContext
import com.byjus.news.injection.module.ApiModule
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ApiModule::class))
class AppModule(private val application: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }
}