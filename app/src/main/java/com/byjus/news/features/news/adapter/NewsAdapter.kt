package com.byjus.news.features.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byjus.news.R
import com.byjus.news.features.news.newsheadlinesmodels.ArticlesItem
import kotlinx.android.synthetic.main.adapter_news_headlines.view.*


/*This class is responsible for holding the view objects through viewHolder*/
class NewsAdapter(
    private val newsList: List<ArticlesItem?>?,
    private var context: Context

) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {


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
        fun bindData(newsData: List<ArticlesItem>, context: Context) {


            for (newsItems in 0 until newsData.size) {

                val urlToImage = newsData[newsItems].urlToImage
                if (urlToImage != null) {
                    if (urlToImage.isNotEmpty()) {

                        Glide.with(context).load(urlToImage).into(view.iv_news_source)
                    }
                }

                val title = newsData[newsItems].title
                if (title?.isNotEmpty()!!) {
                    view.tv_main_headline.text = title
                }

                val newsSource = newsData[newsItems].source
                if (newsSource?.name?.isNotEmpty()!!) {

                    view.tv_news_source.text = newsSource.name
                }

                val newsPublishedAt = newsData[newsItems].publishedAt

                if (newsPublishedAt != null) {
                    if (newsPublishedAt.isNotEmpty()) {

                        view.tv_news_published_at.text = newsPublishedAt
                    }
                }


            }


        }


        companion object {

            private val PHOTO_KEY = "PHOTO"

        }
    }


}