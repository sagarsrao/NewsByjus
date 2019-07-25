package com.byjus.news

import android.app.Application
import android.content.Context
import com.byjus.news.injection.component.AppComponent
import com.byjus.news.injection.component.DaggerAppComponent
import com.byjus.news.injection.module.AppModule
import com.byjus.news.injection.module.NetworkModule

import timber.log.Timber


/*This is the starting point of the application*/
class NewsAppStarterApplication : Application() {

    private var appComponent: AppComponent? = null

    companion object {
        operator fun get(context: Context): NewsAppStarterApplication {
            return context.applicationContext as NewsAppStarterApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

        }
    }

    // Needed to replace the component with a test specific one
    var component: AppComponent
        get() {
            if (appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .networkModule(NetworkModule(this))
                    .build()
            }
            return appComponent as AppComponent
        }
        set(appComponent) {
            this.appComponent = appComponent
        }

}