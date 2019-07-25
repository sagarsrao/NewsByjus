package com.byjus.news.data

import javax.inject.Inject
import javax.inject.Singleton



/*This class is responsible for managaing the api sources from external sources i.e., FactRestServiceApi*/
@Singleton
class DataManager @Inject constructor(private val factRestServiceApi: NewsRestServiceApi) {

    /*fun getTheDataFacts(): Observable<ResFactDataList> {

        return factRestServiceApi.getTheFactDetails()
    }*/
}