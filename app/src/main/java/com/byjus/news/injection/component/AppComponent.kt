package com.byjus.news.injection.component

import android.app.Application
import android.content.Context
import com.byjus.news.data.DataManager
import com.byjus.news.data.NewsRestServiceApi
import com.byjus.news.injection.ApplicationContext
import com.byjus.news.injection.module.AppModule

import dagger.Component

import javax.inject.Singleton


/*The below contains all the project level dependecies*/
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun factApi(): NewsRestServiceApi
}
