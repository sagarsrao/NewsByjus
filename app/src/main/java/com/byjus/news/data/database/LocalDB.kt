package com.byjus.news.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(News::class), version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun newsDao(): NewsDAO


    companion object {
        @Volatile
        private var INSTANCE: LocalDB? = null

        fun getDatabase(context: Context): LocalDB {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDB::class.java,
                    "news_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

