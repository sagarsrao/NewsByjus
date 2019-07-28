@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.byjus.news.features.newsdetails

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.app.NavUtils
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.byjus.news.BuildConfig
import com.byjus.news.R
import com.byjus.news.data.local.PreferencesHelper
import com.byjus.news.features.base.BaseActivity
import com.byjus.news.features.news.newsheadlinesmodels.ResponseNewsHeadlines
import com.byjus.news.features.util.Constants
import com.byjus.news.features.util.NetworkUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.adapter_news_headlines.view.*
import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class NewsDetailsActivity : BaseActivity(), NewsDetailsActivityMVPView {


    @Inject
    lateinit var mPresenter: NewsDetailsActivityPresenter

    lateinit var mPrefrences: PreferencesHelper


    override fun layoutId(): Int {

        return R.layout.activity_news_details
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this@NewsDetailsActivity, error.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progress_headlines_details.visibility = View.VISIBLE

    }

    override fun hideProgress() {

        progress_headlines_details.visibility = View.GONE
    }

    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        iv_fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        //fullscreen_content_controls.visibility = View.VISIBLE
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityComponent().inject(this)
        mPresenter.attachView(this)
        mPrefrences = PreferencesHelper(this)

        //mVisible = true

        if (NetworkUtil.isNetworkConnected(this@NewsDetailsActivity)) {
            val newsPic = intent.extras.getString(Constants.URL_TO_IMAGE)
            val title = intent.extras.getString(Constants.TITLE)
            val sourceName = intent.extras.getString(Constants.SOURCE_NAME)
            val publishedAT = intent.extras.getString(Constants.PUBLISHED_AT)
            val desc = intent.extras.getString(Constants.DESCRIPTION)
            if (newsPic.isNotEmpty()) {
                Glide.with(this).load(newsPic).into(iv_fullscreen_content)
            }
            if (title.isNotEmpty()) {
                Log.d("title", "" + title)
                tv_detail_main_title.text = title
            }
            if (sourceName.isNotEmpty()) {
                tv_details_source_name.text = sourceName
            }
            if (publishedAT.isNotEmpty()) {
                tv_details_published_at.text = publishedAT
            }
            if(desc.isNotEmpty()) {
                tv_detail_desc.text = desc
            }
        } else {
            showInternetErrorMessage()
        }

        iv_detail_nav_back.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    private fun showInternetErrorMessage() {
        val showSnackError = Snackbar.make(frame_news_details, "", Snackbar.LENGTH_LONG)
        showSnackError.setText("No Internet Connection")
        showSnackError.setAction("Retry") {


        }

        showSnackError.setActionTextColor(Color.RED)
        showSnackError.show()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        //fullscreen_content_controls.visibility = View.GONE
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        iv_fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
}
