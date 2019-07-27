package com.byjus.news.features.news

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byjus.news.BuildConfig
import com.byjus.news.R
import com.byjus.news.data.local.PreferencesHelper
import com.byjus.news.features.base.BaseActivity
import com.byjus.news.features.news.adapter.NewsAdapter
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

    }

    override fun layoutId(): Int {
        return R.layout.activity_news
    }

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


        if (NetworkUtil.isNetworkConnected(this@NewsActivity)) {
            mPresenter.getHeadlines("us", BuildConfig.MY_NEWS_API_KEY)
        } else {
            showInternetErrorMessage()
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
