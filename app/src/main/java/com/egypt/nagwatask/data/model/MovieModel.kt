package com.egypt.nagwatask.data.model

import com.google.gson.annotations.SerializedName


data class MovieModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("type")
    var type: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("name")
    var name: String = ""
)