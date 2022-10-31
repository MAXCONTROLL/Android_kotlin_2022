package com.alanzlykh.hw1_square_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alanzlykh.hw1_square_list.R

private const val TOTAL_BLOCKS = "TOTAL_BLOCKS_COUNT"

class BlockListViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    val blocks = mutableListOf<Square>()
    var totalSquares
        get() = savedStateHandle[TOTAL_BLOCKS] ?: 0
        set(value) = savedStateHandle.set(TOTAL_BLOCKS, value)

    init {
        for (i in 0 until totalSquares) {
            addBlock()
            totalSquares--
        }
    }

    fun addBlock() {
        val newValue = blocks.size
        blocks.add(Square(
            newValue + 1,
            if (isEven(newValue)) R.color.blue else R.color.red))
        totalSquares++
    }

    private fun isEven(value: Int): Boolean {
        return value % 2 == 0
    }
}