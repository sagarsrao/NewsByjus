package com.byjus.news

import android.app.Application
import android.content.Context
import com.byjus.news.data.DataManager
import com.byjus.news.data.NewsRestServiceApi
import com.byjus.news.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class ApplicationTestModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }

    /*************
     * MOCKS
     */

    @Provides
    @Singleton
    internal fun providesDataManager(): DataManager {
        return Mockito.mock(DataManager::class.java)
    }

    @Provides
    @Singleton
    internal fun provideMvpBoilerplateService(): NewsRestServiceApi {
        return Mockito.mock(NewsRestServiceApi::class.java)
    }

}