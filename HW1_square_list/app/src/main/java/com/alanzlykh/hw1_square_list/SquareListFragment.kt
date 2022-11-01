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

class SquareListFragment : Fragment() {

    companion object {
        const val ORIENTATION_LANDSCAPE_SPAN_COUNT = 4
        const val ORIENTATION_PORTRAIT_SPAN_COUNT = 3
    }

    private var _binding: FragmentSquareListBinding? = null
    private val binding: FragmentSquareListBinding
        get() = _binding!!

    private val currentOrientation
        get() = resources.configuration.orientation
    private val adapter
        get() = binding.rvSquareList.adapter

    private val blockListViewModel: BlockListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSquareListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSquareList.layoutManager = when (currentOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(
                context,
                ORIENTATION_LANDSCAPE_SPAN_COUNT
            )
            else -> GridLayoutManager(context, ORIENTATION_PORTRAIT_SPAN_COUNT)
        }
        val adapter = BlockListAdapter(blockListViewModel.blocks)
        binding.rvSquareList.adapter = adapter
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