package com.fairfaxmedia.newsapiclient.data.repository

import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.data.model.News
import com.fairfaxmedia.newsapiclient.data.repository.dataSource.NewsLocalDataSource
import com.fairfaxmedia.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.fairfaxmedia.newsapiclient.data.util.Resource
import com.fairfaxmedia.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
        private val newsRemoteDataSource: NewsRemoteDataSource,
        private val newsLocalDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(): Resource<News> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines())
    }

    override suspend fun getSearchedNews(): Resource<News> {
        return responseToResource(
            newsRemoteDataSource.getSearchedNews()
        )
    }

    private fun responseToResource(response:Response<News>):Resource<News>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    

    override suspend fun saveNews(asset: Asset) {
        newsLocalDataSource.saveArticleToDB(asset)
    }

    override suspend fun deleteNews(asset: Asset) {
        newsLocalDataSource.deleteArticlesFromDB(asset)
    }

    override fun getSavedNews(): Flow<List<Asset>> {
        return newsLocalDataSource.getSavedArticles()
    }
}