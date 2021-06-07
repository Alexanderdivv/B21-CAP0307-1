package com.project1.fatigueapplication.viewmodel

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.project1.fatigueapplication.data.api.ApiConfig.provideApiService
import com.project1.fatigueapplication.data.api.MLRequest
import com.project1.fatigueapplication.data.api.MLResponses
import com.project1.fatigueapplication.data.api.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class MainActivityViewModel : ViewModel() { private val _submittedUser: MutableLiveData<Resource<String>> = MutableLiveData()

    private val _uploadPhoto: MutableLiveData<Resource<String>> = MutableLiveData()
    val uploadPhoto: LiveData<Resource<String>> get() = _uploadPhoto

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    fun upload(faceImage: Bitmap) {
        _uploadPhoto.value = Resource.Loading()

        mlReceived = false
        fbUploaded = false
        mlResponse = null

        val imagesRef = storageRef.child("X.jpg")

        val baos = ByteArrayOutputStream()
        faceImage.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = imagesRef.putBytes(data)
        uploadTask.addOnFailureListener {
            _uploadPhoto.value = Resource.Error(it.message.toString())
        }.addOnSuccessListener {
            fbUploaded = true
            checkUploaded()
        }

        val byteArrayOutputStream = ByteArrayOutputStream()
        faceImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        val client = provideApiService().getVerdict(
            MLRequest(
                encoded
            )
        )
        client.enqueue(object : Callback<List<MLResponses>> {
            override fun onResponse(
                call: Call<List<MLResponses>>,
                response: Response<List<MLResponses>>
            ) {
                mlReceived = true
                mlResponse = response.body()?.get(0)?.verdict
                checkUploaded()
            }

            override fun onFailure(call: Call<List<MLResponses>>, t: Throwable) {
                _uploadPhoto.value = Resource.Error(t.message.toString())
            }

        })
    }

    private var mlReceived: Boolean = false
    private var fbUploaded: Boolean = false
    private var mlResponse: String? = null

    fun checkUploaded() {
        if (mlReceived && fbUploaded)
            _uploadPhoto.value =
                if (mlResponse.isNullOrBlank()) Resource.Error("Verdict error")
                else Resource.Success(mlResponse!!)
    }

}