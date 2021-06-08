package com.project1.fatigueapplication.data.api

import com.google.gson.annotations.SerializedName

data class MLResponse(

    @field:SerializedName("verdict")
    val verdict: String,

)

