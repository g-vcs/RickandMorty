package com.guilherme.rickandmortyapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.guilherme.rickandmortyapi.network.Character
import com.guilherme.rickandmortyapi.network.CharacterViewModel

class InfoAdapter(val characterList: List<Character>) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    lateinit var viewModel: CharacterViewModel

    inner class InfoViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)

            name.text = character.name
            image.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bindData(characterList[position])
    }
}