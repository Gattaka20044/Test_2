package com.bignerdranch.android.test2

import com.google.gson.annotations.SerializedName

data class NewsItem(
    var source: String = "",
    @SerializedName("title") var title: String = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String = ""
    )