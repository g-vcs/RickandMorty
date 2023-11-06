package com.guilherme.rickandmortyapi.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoViewModel : ViewModel() {

    private val page: Int = 1

    private var _infoLiveData = MutableLiveData<List<Info>>()
    val infoLiveData: LiveData<List<Info>>
        get() = _infoLiveData

    init {
        getInfoPages(page)
    }

    fun getInfoPages(page: Int) {
        val client = ApiClient.apiService.fetchInfo(page.toString())

        client.enqueue(object : Callback<InfoResponse> {

            override fun onResponse(call: Call<InfoResponse>,response: Response<InfoResponse>) {
                if (response.isSuccessful) {
                    Log.d("checkpage", "" + response.body())
                    _infoLiveData.postValue(response.body()?.info)
                }
            }

            override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }
        })
    }
}