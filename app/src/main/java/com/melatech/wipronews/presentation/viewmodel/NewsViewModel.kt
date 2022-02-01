package com.melatech.wipronews.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.melatech.wipronews.data.model.APIResponse
import com.melatech.wipronews.data.model.Article
import com.melatech.wipronews.data.util.Resource
import com.melatech.wipronews.domain.usecase.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 *created by Jason Junior Calvert on 28/01/2022.
 */
class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase

) : AndroidViewModel(app) {
    val newsHeadLines_m: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val searchedNews_m: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    //Execute a network task
    fun getNewsHeadLines_v(country: String, page: Int) = viewModelScope.launch(IO) {
        newsHeadLines_m.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                //Get a successful response
                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines_m.postValue(apiResult)
            } else {
                newsHeadLines_m.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            newsHeadLines_m.postValue(Resource.Error(e.message.toString()))
        }
    }

    //check network availability
    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager =
            context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
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
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
        }
        return true
    }

    //search
    fun searchNews_v(country: String, searchQuery: String, page: Int) = viewModelScope.launch(IO) {
        searchedNews_m.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                //Get a successful response
                val apiResult = getSearchedNewsUseCase.execute(country, searchQuery, page)
                searchedNews_m.postValue(apiResult)
            }else{
                searchedNews_m.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e: Exception){
            searchedNews_m.postValue(Resource.Error(e.message.toString()))
        }
    }

    //local data

    fun saveArticle_v(article: Article) = viewModelScope.launch(IO) {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNews_v() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteArticle_v(article: Article) = viewModelScope.launch(IO) {
        deleteSavedNewsUseCase.execute(article)

    }


}