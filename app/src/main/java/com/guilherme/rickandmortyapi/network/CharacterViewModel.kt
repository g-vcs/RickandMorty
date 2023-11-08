package com.guilherme.rickandmortyapi.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {

    private val page:String = "1"

    private var _characterLiveData = MutableLiveData<List<Character>>()
    val pokemonLiveData: LiveData<List<Character>>
        get() = _characterLiveData

    init {
        getCharacterApiResult(page)
    }

    fun getCharacterApiResult(page: String) {
        val client = ApiClient.apiService.fetchCharacter(page)

        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>,response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())
                    _characterLiveData.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }

        })
    }
}