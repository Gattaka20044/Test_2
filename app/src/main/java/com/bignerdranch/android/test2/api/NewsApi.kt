package com.bignerdranch.android.test2.api

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    //8afc9d45151143fb954b13ce48ee26f7
    @GET("v2/everything?q=Apple&from=2022-07-21&sortBy=popularity&apiKey=8afc9d45151143fb954b13ce48ee26f7")
    fun fetchNews(): Call<NewsResponse>
}