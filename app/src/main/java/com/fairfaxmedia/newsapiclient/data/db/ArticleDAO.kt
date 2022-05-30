package com.fairfaxmedia.newsapiclient.data.db

import androidx.room.*
import com.fairfaxmedia.newsapiclient.data.model.Asset
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Asset)

    @Query("SELECT * FROM Asset")
    fun getAllArticles(): Flow<List<Asset>>

    @Delete
    suspend fun deleteArticle(article: Asset)



}