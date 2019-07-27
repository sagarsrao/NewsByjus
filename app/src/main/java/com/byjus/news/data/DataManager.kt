package com.byjus.news.data

import com.byjus.news.features.news.newsheadlinesmodels.ResponseNewsHeadlines
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton



/*This class is responsible for managaing the api sources from external sources i.e., FactRestServiceApi*/
@Singleton
class DataManager @Inject constructor(private val factRestServiceApi: NewsRestServiceApi) {

    fun getTheNewsHeadlines(countryName:String?,apikey:String?): Observable<ResponseNewsHeadlines> {

        return factRestServiceApi.getTheNewsDetails(countryName,apikey)
    }
}