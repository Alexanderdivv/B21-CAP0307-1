package com.project1.fatigueapplication.data.api

import com.google.gson.annotations.SerializedName

data class MLRequest(

    @field:SerializedName("gambar")
    val image: String,
)
