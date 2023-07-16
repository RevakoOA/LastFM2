package com.ostapr.lastfm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ostapr.lastfm.R
import com.ostapr.lastfm.com.ostapr.lastfm.main.ArtistsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var adapter: ArtistsListAdapter? = null

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_grid, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArtistsListAdapter()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.artists.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        adapter = null
        recyclerView = null
        super.onDestroyView()
    }
}
