package com.byjus.news.features.news

import android.annotation.SuppressLint
import com.byjus.news.data.DataManager
import com.byjus.news.features.base.BasePresenter
import com.byjus.news.features.util.Exceptions
import com.byjus.news.injection.ConfigPersistent
import io.reactivex.Single

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/*This is the presenter class which contains api functions or the external source functions which connects
 to the view*/
@ConfigPersistent
class NewsActivityPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<NewsActivityMVPView>() {


    @SuppressLint("CheckResult")
    fun getHeadlines(countryName: String?, apikey: String?) {
        checkViewAttached()
        mvpView?.showProgress()
        dataManager.getTheNewsHeadlines(countryName, apikey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resHeadlines ->

                if (resHeadlines.status.equals("ok", true)) {
                    mvpView?.apply {
                        hideProgress()
                        getTheNewsHeadlinesRes(resHeadlines)


                    }
                }


            }, { error ->

                mvpView?.apply {

                    hideProgress()

                    when (error) {
                        is SocketTimeoutException -> showError(Exceptions.NoNetworkException(error))
                        is UnknownHostException -> showError(Exceptions.ServerUnreachableException(error))
                        is HttpException -> showError(Exceptions.HttpCallFailureException(error))
                        else -> showError(error)
                    }

                }
            })


    }


}