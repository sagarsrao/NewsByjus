package com.byjus.news.features.news

import com.byjus.news.features.base.MvpView
import com.byjus.news.features.news.newsheadlinesmodels.ResponseNewsHeadlines


/*This class acts as an view interface class which contains  view related and the API response view functions
* coming from the presenter*/
interface NewsActivityMVPView : MvpView {



    fun showError(error: Throwable)


    fun getTheNewsHeadlinesRes(resHeadlines: ResponseNewsHeadlines)


}