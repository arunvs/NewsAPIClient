package com.fairfaxmedia.newsapiclient.domain.usecase

import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Asset)=newsRepository.deleteNews(article)
}