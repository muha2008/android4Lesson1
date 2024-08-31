package com.example.android4lesson1.ui.fragments.anime

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4lesson1.R
import com.example.android4lesson1.databinding.FragmentAnimeBinding
import com.example.android4lesson1.ui.adapters.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeFragment : Fragment(R.layout.fragment_anime) {

   private val binding by viewBinding(FragmentAnimeBinding::bind)
    private val viewModel by viewModels<AnimeViewModel>()
    private val animeAdapter = AnimeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserver()
        animeLaunch()

    }

    private fun animeLaunch() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                animeAdapter.loadStateFlow.collect {
                    progressBar.isVisible = it.source.refresh is LoadState.Loading
                    appendProgress.isVisible = it.source.append is LoadState.Loading

                }
            }
        }
    }

    private fun initialize() = with(binding) {
        rvAnime.adapter = animeAdapter
    }

    private fun setupObserver() {
        viewModel.fetchAnime().observe(viewLifecycleOwner) {anime ->
            viewLifecycleOwner.lifecycleScope.launch {
                animeAdapter.submitData(anime)
                Log.e("Anime", "$anime")
            }
        }
    }

}