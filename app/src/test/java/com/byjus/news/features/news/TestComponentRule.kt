package com.byjus.news.features.news

import android.content.Context
import com.byjus.news.ApplicationTestModule
import com.byjus.news.NewsAppStarterApplication
import com.byjus.news.TestComponent
import com.byjus.news.data.DataManager
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(val context: Context) : TestRule {

    val testComponent: TestComponent

    init {
        val application = NewsAppStarterApplication.get(context)
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
    }

    val mockDataManager: DataManager
        get() = testComponent.dataManager()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                val application = NewsAppStarterApplication.get(context)
                application.component = testComponent
                base.evaluate()
            }
        }
    }
}