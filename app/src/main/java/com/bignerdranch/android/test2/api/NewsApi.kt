package com.bignerdranch.android.test2.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi {

    @GET("v2/everything?q=Apple&from=2022-07-21&sortBy=popularity&apiKey=8afc9d45151143fb954b13ce48ee26f7")
    fun fetchNews(): Call<ArticlesResponse>

    @GET
    fun funUrlBytes(@Url url: String): Call<ResponseBody>
}