package com.soten.kiosk.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.soten.kiosk.databinding.ActivityMainBinding
import com.soten.kiosk.ui.adapter.MenuAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MenuViewModel>()

    private val menuAdapter by lazy { MenuAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeData()
    }

    private fun bindView() {
        binding.menuRecyclerView.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun observeData() {
        viewModel.checkedLiveData.observe(this) {
            when (it) {
                0 -> setChipGroup(listOf("전", "떡볶이", "치킨")) // 시그니처
                1 -> setChipGroup(listOf("분식", "보쌈", "찜닭")) // 특별
                2 -> setChipGroup(listOf("쥐포", "튀김", "어묵", "오징어")) // 일반
                3 -> setChipGroup(listOf("메론", "파인애플")) // 과일
                4 -> setChipGroup(listOf("술", "음료수")) // 주류
            }
        }

        viewModel.menuListLiveData.observe(this) {
            bindView()
            menuAdapter.submitList(it)
        }
    }

    private fun setChipGroup(filterList: List<String>) {
        binding.filterChipGroup.removeAllViews()
        filterList.forEach { menuFilter ->
            binding.filterChipGroup.addView(
                Chip(this).apply {
                    text = menuFilter
                }
            )
        }
    }

}