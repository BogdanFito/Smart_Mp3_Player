package com.plcoding.spotifycloneyt.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.adapters.SongAdapter
import com.plcoding.spotifycloneyt.other.Constants.CURRENT_MOOD
import com.plcoding.spotifycloneyt.other.Constants.FUNNY_MOOD
import com.plcoding.spotifycloneyt.other.Constants.SAD_MOOD
import com.plcoding.spotifycloneyt.other.Constants.SLEEPY_MOOD
import com.plcoding.spotifycloneyt.other.Status
import com.plcoding.spotifycloneyt.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var songAdapter: SongAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView()
        subscribeToObservers()

        songAdapter.setOnItemClickListener {
            mainViewModel.playOrToggleSong(it)
        }
    }

    private fun setupRecyclerView() = rvAllSongs.apply {
        adapter = songAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(viewLifecycleOwner) { result ->
            when(result.status) {
                Status.SUCCESS -> {
                    allSongsProgressBar.isVisible = false
                    if (CURRENT_MOOD.isEmpty()) result.data?.let { songs ->
                        songAdapter.songs = songs
                    } else if (CURRENT_MOOD.equals(SAD_MOOD)) result.data?.let { songs ->
                        songAdapter.songs = songs.filter { it.tag.equals(SAD_MOOD) }
                    } else if (CURRENT_MOOD.equals(FUNNY_MOOD)) result.data?.let { songs ->
                        songAdapter.songs = songs.filter { it.tag.equals(FUNNY_MOOD) }
                    } else if (CURRENT_MOOD.equals(SLEEPY_MOOD)) result.data?.let { songs ->
                        songAdapter.songs = songs.filter { it.tag.equals(SLEEPY_MOOD) }
                    }
                }
                Status.ERROR -> Unit
                Status.LOADING -> allSongsProgressBar.isVisible = true
            }
        }
    }
}



