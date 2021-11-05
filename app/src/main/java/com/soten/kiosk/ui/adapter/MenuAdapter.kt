package com.soten.kiosk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soten.kiosk.ui.adapter.viewholder.MenuViewHolder
import com.soten.kiosk.databinding.ItemMenuBinding
import com.soten.kiosk.model.Menu

class MenuAdapter : ListAdapter<Menu, MenuViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MenuViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean =
                oldItem == newItem
        }
    }

}