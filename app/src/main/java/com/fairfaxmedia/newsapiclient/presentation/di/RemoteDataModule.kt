package com.fairfaxmedia.newsapiclient.presentation.di

import com.fairfaxmedia.newsapiclient.data.api.NewsAPIService
import com.fairfaxmedia.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.fairfaxmedia.newsapiclient.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ):NewsRemoteDataSource{
       return NewsRemoteDataSourceImpl(newsAPIService)
    }

}












