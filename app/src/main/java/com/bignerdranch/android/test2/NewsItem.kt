package com.bignerdranch.android.test2

import com.google.gson.annotations.SerializedName


data class NewsItem(
    var author: String = "",
    @SerializedName("publishedAt") var data: String = "",
    var title: String = "",
    var description: String = "",
)