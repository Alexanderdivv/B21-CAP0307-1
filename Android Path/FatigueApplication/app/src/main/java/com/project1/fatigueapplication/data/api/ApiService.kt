package com.project1.fatigueapplication.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("predict")
    fun getVerdict(@Body body: MLRequest): Call<List<MLResponses>>

}