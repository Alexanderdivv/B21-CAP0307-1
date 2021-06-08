package com.project1.fatigueapplication.viewmodel

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project1.fatigueapplication.data.api.ApiConfig.provideApiService
import com.project1.fatigueapplication.data.api.MLRequest
import com.project1.fatigueapplication.data.api.MLResponse
import com.project1.fatigueapplication.data.api.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class MainActivityViewModel : ViewModel() {
    private var mlReceived: Boolean = false
    private var fbUploaded: Boolean = false
    private var mlResponse: String? = null
    private val _uploadPhoto: MutableLiveData<Resource<String>> = MutableLiveData()
    val uploadPhoto: LiveData<Resource<String>> get() = _uploadPhoto
    fun upload(imagePros: Bitmap) {

        if (mlResponse.isNullOrBlank()) {
            _uploadPhoto.value = Resource.Error("No Image Uploaded")
            return
        }
        val baos = ByteArrayOutputStream()
        imagePros.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()
        val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
        val client = provideApiService().getVerdict(
            MLRequest(
                encoded
            )
        )
        client.enqueue(object : Callback<List<MLResponse>> {
                override fun onResponse(
                    call: Call<List<MLResponse>>,
                    response: Response<List<MLResponse>>
                ) {
                    mlReceived = true
                    mlResponse = response.body()?.get(0)?.verdict
                    checkUploaded()
                }

                override fun onFailure(call: Call<List<MLResponse>>, t: Throwable) {
                    _uploadPhoto.value = Resource.Error(t.message.toString())
                }

            }
        )
    }
    fun checkUploaded() {
        if (mlReceived && fbUploaded)
            _uploadPhoto.value =
                if (mlResponse.isNullOrBlank()) Resource.Error("Verdict error")
                else Resource.Success(mlResponse!!)
    }
}
