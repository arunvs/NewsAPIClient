package com.fairfaxmedia.newsapiclient.domain.usecase

import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
  suspend fun execute(article: Asset)=newsRepository.saveNews(article)
}