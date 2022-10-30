package com.alanzlykh.hw1_square_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanzlykh.hw1_square_list.databinding.ActivityMainBinding
import com.alanzlykh.hw1_square_list.SquareListFragment

class MainActivity : AppCompatActivity() {
    // View Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnAddSquareItem.setOnClickListener { btnAddSquareOnClick() }
        setContentView(binding.root)
    }
    // Add button via Fragment
    private fun btnAddSquareOnClick() {
        val blockList = binding.fragmentContainer.getFragment<SquareListFragment>()
        blockList.addItem()
    }
}