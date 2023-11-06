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

    private var _infoLiveData = MutableLiveData<String>()
    val infoLiveData: LiveData<String>
        get() = _infoLiveData

    init {
        getInfoPages(page)
    }

    fun getInfoPages(page: Int) {
        val client = ApiClient.apiService.fetchInfo(page.toString())

        client.enqueue(object : Callback<Info> {

            override fun onResponse(call: Call<Info>,response: Response<Info>) {
                if (response.isSuccessful) {
                    Log.d("checkpage", "" + response.body())
                    _infoLiveData.postValue(response.body()?.next)
                }
            }

            override fun onFailure(call: Call<Info>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }


        })
    }
}