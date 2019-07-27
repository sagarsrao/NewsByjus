package com.byjus.news.data

import com.byjus.news.features.news.newsheadlinesmodels.ResponseNewsHeadlines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


/*This class is responsible for listing down all the get and post api's*/
interface NewsRestServiceApi {


    @GET("/v2/top-headlines")
    fun getTheNewsDetails(
         @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Observable<ResponseNewsHeadlines>

}