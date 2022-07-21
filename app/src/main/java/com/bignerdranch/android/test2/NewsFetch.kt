package com.bignerdranch.android.test2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.test2.api.NewsApi
import com.bignerdranch.android.test2.api.NewsData
import com.bignerdranch.android.test2.api.NewsResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "NewsFetch"

class NewsFetch {
    private val newsApi: NewsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApi = retrofit.create(NewsApi::class.java)
    }

    fun fetchNews(): LiveData<List<NewsItem>> {
        val responseLiveData: MutableLiveData<List<NewsItem>> = MutableLiveData()
        val newsRequest: Call<NewsResponse> = newsApi.fetchNews()

        newsRequest.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d(TAG, "Response received")
                val newsResponse: NewsResponse? = response.body()
                val newsDataResponse: NewsData? = newsResponse?.news
                var newsItems: List<NewsItem> = newsDataResponse?.newsItems ?: mutableListOf()
                newsItems = newsItems.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = newsItems
            }
        })

        return responseLiveData
    }
}