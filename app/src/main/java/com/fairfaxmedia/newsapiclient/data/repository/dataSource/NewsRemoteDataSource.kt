package com.fairfaxmedia.newsapiclient.data.repository.dataSource

import com.fairfaxmedia.newsapiclient.data.model.News
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines():Response<News>
    suspend fun getSearchedNews():Response<News>
}