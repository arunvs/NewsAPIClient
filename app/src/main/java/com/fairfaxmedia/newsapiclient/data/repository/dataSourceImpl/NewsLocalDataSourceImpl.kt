package com.fairfaxmedia.newsapiclient.data.repository.dataSourceImpl

import com.fairfaxmedia.newsapiclient.data.db.ArticleDAO
import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Asset) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Asset>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticlesFromDB(article: Asset) {
        articleDAO.deleteArticle(article)
    }
}