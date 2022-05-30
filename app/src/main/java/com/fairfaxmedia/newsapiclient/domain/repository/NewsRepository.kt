package com.fairfaxmedia.newsapiclient.domain.repository

import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.data.model.News
import com.fairfaxmedia.newsapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository{

      suspend fun getNewsHeadlines(): Resource<News>
      suspend fun getSearchedNews() : Resource<News>
      suspend fun saveNews(article: Asset)
      suspend fun deleteNews(article: Asset)
      fun getSavedNews(): Flow<List<Asset>>




}