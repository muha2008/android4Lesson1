package com.example.android4lesson1.data.remote.models

import com.google.gson.annotations.SerializedName

data class KitsuResponse(
    @SerializedName("data")
    val data: List<DataItem>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("links")
    val links: Links
)