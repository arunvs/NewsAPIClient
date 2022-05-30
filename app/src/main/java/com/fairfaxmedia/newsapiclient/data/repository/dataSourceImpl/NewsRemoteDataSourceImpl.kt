package com.fairfaxmedia.newsapiclient.data.repository.dataSourceImpl

import com.fairfaxmedia.newsapiclient.data.api.NewsAPIService
import com.fairfaxmedia.newsapiclient.data.model.News
import com.fairfaxmedia.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
        private val newsAPIService: NewsAPIService
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): Response<News> {
          return newsAPIService.getTopHeadlines()
    }

    override suspend fun getSearchedNews(): Response<News> {
        return newsAPIService.getSearchedTopHeadlines()
    }
}