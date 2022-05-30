package com.fairfaxmedia.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.data.model.News
import com.fairfaxmedia.newsapiclient.data.util.Resource
import com.fairfaxmedia.newsapiclient.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app:Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {
    val newsHeadLines: MutableLiveData<Resource<News>> = MutableLiveData()

    fun getNewsHeadLines() = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try{
      if(isNetworkAvailable(app)) {

          val apiResult = getNewsHeadlinesUseCase.execute()
          newsHeadLines.postValue(apiResult)
      }else{
          newsHeadLines.postValue(Resource.Error("Internet is not available"))
      }

        }catch (e:Exception){
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    //search
    val searchedNews : MutableLiveData<Resource<News>> = MutableLiveData()

    fun searchNews(
        searchQuery : String,
    ) = viewModelScope.launch {
       searchedNews.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedNewsUseCase.execute(searchQuery)
                val items = response.data?.assets?.toList()?.filter { it.headline.contains(searchQuery) }
                if (items != null) {
                    response.data?.assets = items
                }
                searchedNews.postValue(response)
            } else {
                searchedNews.postValue(Resource.Error("No internet connection"))
            }
        }catch(e:Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }

    //local data
    fun saveArticle(article: Asset) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNews() = liveData{
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteArticle(article: Asset) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }

}














