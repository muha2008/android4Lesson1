package com.example.android4lesson1.data.remote.models

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("last")
    val last: String = "",
    @SerializedName("prev")
    val prev: String = "",
    @SerializedName("first")
    val first: String = ""
)