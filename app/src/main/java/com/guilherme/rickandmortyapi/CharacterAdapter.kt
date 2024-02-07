package com.guilherme.rickandmortyapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.guilherme.rickandmortyapi.network.Character

class CharacterAdapter() :
    PagingDataAdapter<Character , CharacterAdapter.MainViewHolder>(CharacterComparator) {

    lateinit var viewModel: CharacterViewModel

    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)

            name.text = character.name
            image.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }
}
