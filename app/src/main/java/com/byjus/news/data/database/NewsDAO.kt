package com.byjus.news.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byjus.news.features.news.newsheadlinesmodels.ArticlesItem


@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articleList: List<News?>)

    @Query("SELECT * FROM " + DBConstant.NEWS_TABLE_NAME)
    fun getAll():List<News?>



}