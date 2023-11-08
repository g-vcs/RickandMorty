package com.guilherme.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.guilherme.rickandmortyapi.network.ApiClient
import com.guilherme.rickandmortyapi.network.CharacterResponse
import com.guilherme.rickandmortyapi.network.InfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var page = 1
        fetchCharacters(page)

        val nextPageButton = findViewById<Button>(R.id.btn_nextPage)
        nextPageButton.setOnClickListener {
            fetchCharacters(page)
            page++
        }
    }

    fun fetchCharacters(page: Int) {
        val client = ApiClient.apiService.fetchCharacter(page.toString())

        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())
                    val result = response.body()?.result
                    val nextPage = handlePageNumberFormat(response.body()?.info?.next)
                    Log.d("characters", "Next Page = $nextPage")

                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                        recyclerView?.layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter

                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }
        })
    }

    fun handlePageNumberFormat(link: String?): Int? {
        val result =  link?.replace("https://rickandmortyapi.com/api/character?page=", "")?.toInt()
        return result
    }
}