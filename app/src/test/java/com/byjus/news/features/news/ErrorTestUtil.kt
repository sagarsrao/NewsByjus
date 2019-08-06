package com.byjus.news.features.news

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

object ErrorTestUtil {



    fun checkErrorViewsDisplay() {
        Espresso.onView(Matchers.allOf<View>(ViewMatchers.withText("Oops"), ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(Matchers.allOf<View>(ViewMatchers.withText("There was a problem in loading the articles list"), ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}