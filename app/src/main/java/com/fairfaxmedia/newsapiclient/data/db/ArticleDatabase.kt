package com.fairfaxmedia.newsapiclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fairfaxmedia.newsapiclient.data.model.Asset

@Database(
    entities = [Asset::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract  class ArticleDatabase : RoomDatabase(){
    abstract fun getArticleDAO():ArticleDAO
}

