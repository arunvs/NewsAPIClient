package com.fairfaxmedia.newsapiclient.domain.usecase

import com.fairfaxmedia.newsapiclient.data.model.News
import com.fairfaxmedia.newsapiclient.data.util.Resource
import com.fairfaxmedia.newsapiclient.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
     suspend fun execute(searchQuery:String): Resource<News>{
         return newsRepository.getSearchedNews()
     }
}