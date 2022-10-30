package com.alanzlykh.hw1_square_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alanzlykh.hw1_square_list.databinding.ListItemSquareBinding

class SquareHolder(
    private val binding: ListItemSquareBinding
    ): ViewHolder(binding.root) {
    fun bind(square: Square) {
        binding.tvSquare.apply {
            text = square.value.toString()
            setBackgroundResource(square.color)
        }
    }
}

class BlockListAdapter(
    private val square: MutableList<Square> = mutableListOf()
): Adapter<SquareHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSquareBinding.inflate(inflater, parent, false)
        return SquareHolder(binding)
    }

    override fun onBindViewHolder(holder: SquareHolder, position: Int) {
        val block = square[position]
        holder.bind(block)
    }

    override fun getItemCount() = square.size

}