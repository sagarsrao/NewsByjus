@file:Suppress("DEPRECATION")

package com.byjus.news.features.news


import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.byjus.news.TestDataFactory

import org.junit.Test

import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsActivityTest{

    private val component = TestComponentRule(InstrumentationRegistry.getTargetContext())
    private val main = ActivityTestRule(NewsActivity::class.java, false, false)


    @Rule
    @JvmField
    var chain: TestRule = RuleChain.outerRule(component).around(main)





    @Test
    fun checkNewsdDisplay() {

        val articlesList = TestDataFactory.makeArticles()
        main.launchActivity(null)

        for (names in arrayListOf(articlesList)) {
            Espresso.onView(ViewMatchers.withText(names.title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun checkErrorViewDisplays() {
        main.launchActivity(null)
        ErrorTestUtil.checkErrorViewsDisplay()
    }


}