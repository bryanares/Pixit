package com.brian.pixit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brian.pixit.models.Photo
import com.brian.pixit.network.PixitApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoViewModel : ViewModel() {

    private val _response = MutableLiveData<Photo>()
    val response : LiveData<Photo> get() = _response

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _failed = MutableLiveData<String>()
    val failed: LiveData<String>
        get() = _failed

    init {
        getApiResponse()

    }

   fun getApiResponse() {

       viewModelScope.launch {
           PixitApi.apiService.getPhotos().enqueue( object : Callback<Photo> {

               override fun onResponse(call: Call<Photo>, response: Response<Photo>){
                   _response.value = response.body()
                   _loading.value = false
               }

               override fun onFailure(call: Call<Photo>, t: Throwable){
                   _loading.value = false
                   _failed.value = t.localizedMessage
               }
           })
       }


    }
}