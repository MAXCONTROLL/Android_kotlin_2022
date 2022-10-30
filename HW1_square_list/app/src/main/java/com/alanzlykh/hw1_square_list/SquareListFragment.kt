package com.alanzlykh.hw1_square_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.alanzlykh.hw1_square_list.databinding.FragmentSquareListBinding

class SquareListFragment: Fragment() {

    private var _binding: FragmentSquareListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null"
        }

    private val currentOrientation
    get() = resources.configuration.orientation
    private val adapter
    get() = binding.rvSquareList.adapter

    private val blockListViewModel: BlockListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSquareListBinding.inflate(inflater, container, false)
        binding.rvSquareList.layoutManager = when (currentOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(context, 4)
            else -> GridLayoutManager(context, 3)
        }
        val adapter = BlockListAdapter(blockListViewModel.blocks)
        binding.rvSquareList.adapter = adapter
        return binding.root
    }
    fun addItem() {
        blockListViewModel.addBlock()
        adapter?.notifyItemInserted(blockListViewModel.totalSquares - 1)
        binding.rvSquareList.scrollToPosition(blockListViewModel.totalSquares - 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}