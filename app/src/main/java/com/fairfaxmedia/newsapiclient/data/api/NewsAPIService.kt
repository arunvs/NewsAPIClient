package com.fairfaxmedia.newsapiclient.data.api

import com.fairfaxmedia.newsapiclient.data.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPIService {
  @GET("/1/coding_test/13ZZQX/full")
  suspend fun getTopHeadlines(): Response<News>

    @GET("/1/coding_test/13ZZQX/full")
    suspend fun getSearchedTopHeadlines(): Response<News>

}