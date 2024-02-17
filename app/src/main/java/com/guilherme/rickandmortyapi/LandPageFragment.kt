package com.guilherme.rickandmortyapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.guilherme.rickandmortyapi.databinding.FragmentLandPageBinding
import com.guilherme.rickandmortyapi.ui.CharactersScreen
import com.guilherme.rickandmortyapi.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "CharacterFragment"

class LandPageFragment : Fragment() {

    private lateinit var composeView: ComposeView
    private val characterViewModel: CharacterViewModel by viewModels()

    private var _binding: FragmentLandPageBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandPageBinding.inflate(inflater, container, false)
        binding.landpage.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*composeView.setContent {
            val characterList by characterViewModel.characterItem.collectAsState()

            CharactersScreen(characterItems = characterList)
        }*/


        val characterAdapter = CharacterAdapter()

        binding.landpage.apply {
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = characterAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                characterViewModel.characterItem.collectLatest { pagingData ->
                    characterAdapter.submitData(pagingData)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
