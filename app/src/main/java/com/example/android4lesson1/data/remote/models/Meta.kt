package com.example.android4lesson1.data.remote.models

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("count")
    val count: Int = 0
)