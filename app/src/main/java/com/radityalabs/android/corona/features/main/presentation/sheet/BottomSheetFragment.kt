package com.radityalabs.android.corona.features.main.presentation.sheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radityalabs.android.corona.R
import com.radityalabs.android.corona.features.main.presentation.main.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetFragment : Fragment(R.layout.fragment_bottom_sheet) {

    @Inject
    internal lateinit var vm: BottomSheetViewModel

    private lateinit var rvFeeds: RecyclerView
    private lateinit var rvAdapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObserver()
        initData()
    }

    private fun initView(view: View) {
        initRecycleView(view)
    }

    private fun initObserver() {
        vm.feeds.observe(viewLifecycleOwner, (rvAdapter::submitList))
    }

    private fun initData() {
        vm.fetchFeeds()
    }

    private fun initRecycleView(view: View) {
        rvAdapter = MainAdapter()

        rvFeeds = view.findViewById(R.id.feeds)
        rvFeeds.adapter = rvAdapter
        rvFeeds.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance(): BottomSheetFragment {
            return BottomSheetFragment()
        }
    }
}