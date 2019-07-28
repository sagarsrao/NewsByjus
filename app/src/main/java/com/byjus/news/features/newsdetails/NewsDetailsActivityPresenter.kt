package com.byjus.news.features.newsdetails

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
class NewsDetailsActivityPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<NewsDetailsActivityMVPView>() {





}