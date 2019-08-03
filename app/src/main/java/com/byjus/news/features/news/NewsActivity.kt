package com.byjus.news.features.news

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.WorkerThread
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byjus.news.BuildConfig
import com.byjus.news.R
import com.byjus.news.data.database.LocalDB
import com.byjus.news.data.database.News
import com.byjus.news.data.database.NewsDAO
import com.byjus.news.data.local.PreferencesHelper
import com.byjus.news.features.base.BaseActivity
import com.byjus.news.features.news.adapter.NewsAdapter
import com.byjus.news.features.news.adapter.NewsOfflineAdapter
import com.byjus.news.features.news.newsheadlinesmodels.ArticlesItem
import com.byjus.news.features.news.newsheadlinesmodels.ResponseNewsHeadlines
import com.byjus.news.features.util.NetworkUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject

class NewsActivity : BaseActivity(), NewsActivityMVPView {


    @Inject
    lateinit var mPresenter: NewsActivityPresenter

    lateinit var mPreferences: PreferencesHelper

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun getTheNewsHeadlinesRes(resHeadlines: ResponseNewsHeadlines) {


        viewManager = LinearLayoutManager(this)




        viewAdapter = NewsAdapter(resHeadlines.articles, this)



        rv_news.apply {

            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

            adapter!!.notifyDataSetChanged()

        }


        try {
            for (articleItems in 0 until resHeadlines.articles!!.size) {

                val articleRoomList = News(
                    resHeadlines.articles[articleItems]?.publishedAt!!,
                    resHeadlines.articles[articleItems]?.urlToImage!!,
                    resHeadlines.articles[articleItems]?.description!!,
                    resHeadlines.articles[articleItems]?.source?.name!!,
                    resHeadlines.articles[articleItems]?.title!!
                )

                newsDAO?.insertAll(listOf(articleRoomList))



            }

        } catch (e: Exception) {
        }

    }

    override fun layoutId(): Int {
        return R.layout.activity_news
    }


    var newsDAO: NewsDAO? = null
    override fun showError(error: Throwable) {

        val mSnackBar = Snackbar.make(news_coordinator_layout, error.toString(), Snackbar.LENGTH_LONG)
        mSnackBar.show()


    }

    override fun showProgress() {
        progress_headlines.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_headlines.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        activityComponent().inject(this)
        mPresenter.attachView(this)
        mPreferences = PreferencesHelper(this@NewsActivity)

        newsDAO = LocalDB.getDatabase(this).newsDao()

        if (NetworkUtil.isNetworkConnected(this@NewsActivity)) {
            mPresenter.getHeadlines("us", BuildConfig.MY_NEWS_API_KEY)
        } else {


            viewManager = LinearLayoutManager(this)
            viewAdapter = NewsOfflineAdapter(newsDAO!!.getAll(),this)

            rv_news.apply {

                setHasFixedSize(true)

                layoutManager = viewManager

                adapter = viewAdapter

                adapter!!.notifyDataSetChanged()

            }

            //showInternetErrorMessage()
        }


    }

    private fun showInternetErrorMessage() {
        val showSnackError = Snackbar.make(news_coordinator_layout, "", Snackbar.LENGTH_LONG)
        showSnackError.setText("No Internet Connection")
        showSnackError.setAction("Retry") {
            mPresenter.getHeadlines("us", BuildConfig.MY_NEWS_API_KEY)

        }

        showSnackError.setActionTextColor(Color.RED)
        showSnackError.show()

    }
}
