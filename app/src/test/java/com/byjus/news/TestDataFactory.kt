package com.byjus.news

import com.byjus.news.features.news.newsheadlinesmodels.ArticlesItem
import com.byjus.news.features.news.newsheadlinesmodels.Source
import java.util.*

object TestDataFactory {



    fun makeArticles(): ArticlesItem {
        return ArticlesItem("2019-08-06T02:56:00Z","Reuters Editorial",
            "https://s1.reutersmedia.net/resources/r/?m=02&d=20190806&t=2&i=1415879772&w=1200&r=LYNXNPEF7506A", "Amazon.com Inc Chief Executive Officer Jeff Bezos offloaded $990 million worth of shares in the company last Thursday and Friday, taking the total value of shares sold last week to $2.8 billion.",
            Source("Reuters","reuters"),"Jeff Bezos sells Amazon stock worth \$2.8 billion last week - Reuters","\"https://www.reuters.com/article/us-amazon-bezos-stake/jeff-bezos-sells-amazon-stock-worth-2-8-billion-last-week-idUSKCN1UW07H","(Reuters) - Amazon.com Inc (AMZN.O) Chief Executive Officer Jeff Bezos offloaded $990 million worth of shares in the company last Thursday and Friday, taking the total value of shares sold last week to $2.8 billion. \r\nIn the last three days of July, Bezos has… [+639 chars]"
            )

    }


}