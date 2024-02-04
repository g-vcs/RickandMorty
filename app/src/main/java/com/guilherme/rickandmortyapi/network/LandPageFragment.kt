package com.guilherme.rickandmortyapi.network

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilherme.rickandmortyapi.databinding.FragmentLandPageBinding
import kotlinx.coroutines.launch

private const val TAG = "MainFragment"

class LandPageFragment: Fragment() {

    private var _binding: FragmentLandPageBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandPageBinding.inflate(inflater, container, false)
        binding.landpage.layoutManager = LinearLayoutManager(context)
        //binding.landPage.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val response = RickandMortyRepository().fetchCharacters()
            Log.d(TAG, "Response received: $response")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}