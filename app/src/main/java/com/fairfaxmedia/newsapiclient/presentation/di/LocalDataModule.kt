package com.fairfaxmedia.newsapiclient.presentation.di

import com.fairfaxmedia.newsapiclient.data.db.ArticleDAO
import com.fairfaxmedia.newsapiclient.data.repository.dataSource.NewsLocalDataSource
import com.fairfaxmedia.newsapiclient.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
      return NewsLocalDataSourceImpl(articleDAO)
    }

}













