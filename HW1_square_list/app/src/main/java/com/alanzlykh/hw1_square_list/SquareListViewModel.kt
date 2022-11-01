package com.alanzlykh.hw1_square_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alanzlykh.hw1_square_list.R

private const val TOTAL_SQUARES = "TOTAL_SQUARES_COUNT"

class SquareListViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    val squares = mutableListOf<Square>()
    var totalSquares
        get() = savedStateHandle[TOTAL_SQUARES] ?: 0
        set(value) = savedStateHandle.set(TOTAL_SQUARES, value)

    init {
        for (i in 0 until totalSquares) {
            addSquare()
            totalSquares--
        }
    }

    fun addSquare() {
        val newValue = squares.size
        squares.add(Square(
            newValue + 1,
            if (isEven(newValue)) R.color.blue else R.color.red))
        totalSquares++
    }

    private fun isEven(value: Int): Boolean {
        return value % 2 == 0
    }
}