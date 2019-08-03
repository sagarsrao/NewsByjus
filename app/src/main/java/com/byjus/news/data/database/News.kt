package com.byjus.news.data.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.byjus.news.features.news.newsheadlinesmodels.Source
import com.google.gson.annotations.SerializedName


@Entity(tableName = DBConstant.NEWS_TABLE_NAME)
data class News(


    @field:SerializedName("publishedAt")
    @ColumnInfo(name = DBConstant.NEWS_PUBLISHED_AT)
    val publishedAt: String,


    @field:SerializedName("urlToImage")
    @ColumnInfo(name = DBConstant.IMAGE_URL)
    @PrimaryKey
    @NonNull
    val urlToImage: String,

    @field:SerializedName("description")
    @ColumnInfo(name = DBConstant.NEWS_DESCRIPTION)
    val description: String,

    @field:SerializedName("name")
    @ColumnInfo(name = DBConstant.NEWS_SOURCE_NAME)
    val name: String,

    @field:SerializedName("title")
    @ColumnInfo(name = DBConstant.NEWS_TITLE)
    val title: String
)


