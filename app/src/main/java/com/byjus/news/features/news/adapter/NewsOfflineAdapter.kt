package com.byjus.news.features.news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byjus.news.R
import com.byjus.news.data.database.News
import com.byjus.news.features.news.newsheadlinesmodels.ArticlesItem
import com.byjus.news.features.newsdetails.NewsDetailsActivity
import com.byjus.news.features.util.Constants
import com.byjus.news.features.util.Constants.DESCRIPTION
import kotlinx.android.synthetic.main.adapter_news_headlines.view.*


/*This class is responsible for holding the view objects through viewHolder*/
class NewsOfflineAdapter(
    private val newsList: List<News?>?,
    private var context: Context

) :
    RecyclerView.Adapter<NewsOfflineAdapter.NewsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_news_headlines, parent, false)
        return NewsHolder(inflatedView)
    }

    override fun getItemCount(): Int = newsList?.size!!


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

        val nData = newsList?.get(position)!!
        holder.bindData(listOf(nData), context)


    }


    class NewsHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(newsData: List<News>, context: Context) {

            var urlToImage: String? = null
            var title: String? = null
            var newsPublishedAt: String? = null
            var newsSource:String? = null
            var desc:String? = null



            for (newsItems in 0 until newsData.size) {

                urlToImage = newsData[newsItems].urlToImage
                desc = newsData[newsItems].description
                if (urlToImage.isNotEmpty()) {

                    Glide.with(context).load(urlToImage).into(view.iv_news_source)
                }

                title = newsData[newsItems].title
                if (title.isNotEmpty()) {
                    view.tv_main_headline.text = title
                }

                 newsSource = newsData[newsItems].name
                if (newsSource.isNotEmpty()) {

                    view.tv_news_source.text = newsSource
                }

                newsPublishedAt = newsData[newsItems].publishedAt

                if (newsPublishedAt.isNotEmpty()) {

                    view.tv_news_published_at.text = newsPublishedAt.substring(0,10)
                }


            }


            /*onClick of item*/
            view.card_news.setOnClickListener {

                val detailsActivity = Intent(context, NewsDetailsActivity::class.java)

                detailsActivity.putExtra(Constants.URL_TO_IMAGE, urlToImage)
                detailsActivity.putExtra(Constants.TITLE, title)
                detailsActivity.putExtra(Constants.PUBLISHED_AT, newsPublishedAt?.substring(0,10))
                detailsActivity.putExtra(Constants.SOURCE_NAME, newsSource)
                detailsActivity.putExtra(DESCRIPTION,desc)


                context.startActivity(detailsActivity)
            }


        }


        companion object {

            private val PHOTO_KEY = "PHOTO"

        }
    }


}