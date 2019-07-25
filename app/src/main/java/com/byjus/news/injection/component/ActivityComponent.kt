package com.byjus.news.injection.component

import com.byjus.news.features.base.BaseActivity
import com.byjus.news.features.news.NewsActivity
import com.byjus.news.injection.PerActivity
import com.byjus.news.injection.module.ActivityModule

import dagger.Subcomponent




/*The below contains all the activity level components*/
@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(newsActivity: NewsActivity)


}

