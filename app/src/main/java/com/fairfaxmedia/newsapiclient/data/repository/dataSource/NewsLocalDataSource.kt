package com.fairfaxmedia.newsapiclient.data.repository.dataSource

import com.fairfaxmedia.newsapiclient.data.model.Asset
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(asset: Asset)
    fun getSavedArticles(): Flow<List<Asset>>
    suspend fun deleteArticlesFromDB(article: Asset)


}