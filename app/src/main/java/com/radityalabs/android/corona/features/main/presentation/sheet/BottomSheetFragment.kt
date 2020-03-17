package com.radityalabs.android.corona.features.main.presentation.sheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radityalabs.android.corona.R
import com.radityalabs.android.corona.di.Injector
import com.radityalabs.android.corona.features.main.presentation.main.MainAdapter
import kotlinx.coroutines.Dispatchers
import kotlin.LazyThreadSafetyMode.NONE

class BottomSheetFragment : Fragment(R.layout.fragment_bottom_sheet) {
    private val vm by lazy(NONE) { Injector.get<BottomSheetViewModel>() }

    private lateinit var rvFeeds: RecyclerView
    private lateinit var rvAdapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGraph()
        initView(view)
        initObserver()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Injector.remove(vm)
    }

    private fun initGraph() {
        BottomSheetRepositoryImpl(Injector.get())
            .let(::BottomSheetUseCaseImpl)
            .let { BottomSheetViewModel(it, Dispatchers.IO) }
            .let(Injector::add)
    }

    private fun initView(view: View) {
        initRecycleView(view)
    }

    private fun initObserver() {
        vm.feeds.observe(viewLifecycleOwner, Observer {
            rvAdapter.submitList(it)
        })
    }

    private fun initData() {
        vm.fetchFeeds()
    }

    private fun initRecycleView(view: View) {
        rvAdapter =
            MainAdapter()

        rvFeeds = view.findViewById(R.id.feeds)
        rvFeeds.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        fun newInstance(): BottomSheetFragment {
            return BottomSheetFragment()
        }
    }
}