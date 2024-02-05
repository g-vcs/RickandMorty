package com.guilherme.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD

=======
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.guilherme.rickandmortyapi.network.CharacterViewModel
import com.guilherme.rickandmortyapi.network.Info
import com.guilherme.rickandmortyapi.network.InfoViewModel
>>>>>>> main

class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    private val infoViewModel: InfoViewModel by lazy {
        ViewModelProvider(this).get(InfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD


=======
        val page = infoViewModel.infoLiveData
        Log.d("pageLiveData", "${infoViewModel.infoLiveData.value}")

        infoViewModel.infoLiveData.observe(this){
            Log.d("pageLiveData", "${it.get(0)}")

        }


        viewModel.pokemonLiveData.observe(this){result ->
            val adapter = MainAdapter(result)
            val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
            recyclerView?.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView?.adapter = adapter

            val nextPage = findViewById<Button>(R.id.btn_next_page)
            nextPage.setOnClickListener {
                viewModel.getCharacterApiResult(infoViewModel.infoLiveData)
            }
        }
>>>>>>> main
    }
}