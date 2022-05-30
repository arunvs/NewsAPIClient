package com.fairfaxmedia.newsapiclient.domain.usecase

import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Asset>>{
        return newsRepository.getSavedNews()
    }
}