package com.byjus.news.features.news.newsheadlinesmodels

import com.google.gson.annotations.SerializedName

data class ResponseNewsHeadlines(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)