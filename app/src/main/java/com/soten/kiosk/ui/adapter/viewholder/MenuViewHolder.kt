package com.soten.kiosk.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.kiosk.databinding.ItemMenuBinding
import com.soten.kiosk.model.Menu

class MenuViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(menu: Menu) {
        binding.menuImage.load(menu.url)
    }

}