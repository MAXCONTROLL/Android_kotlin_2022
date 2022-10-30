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
        if (totalSquares != 0) {
            for (i in 0 until totalSquares) {
                addBlock()
                totalSquares--
            }
        }
    }
    fun addBlock() {
        val newValue = blocks.size
        if (newValue % 2 == 0) blocks.add(Square(newValue + 1, R.color.blue))
        else blocks.add(Square(newValue + 1, R.color.red))
        totalSquares++
    }
}