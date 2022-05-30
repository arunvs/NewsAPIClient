package com.fairfaxmedia.newsapiclient.domain.usecase

import com.fairfaxmedia.newsapiclient.data.model.News
import com.fairfaxmedia.newsapiclient.data.util.Resource
import com.fairfaxmedia.newsapiclient.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(): Resource<News>{
        return newsRepository.getNewsHeadlines()
    }
}