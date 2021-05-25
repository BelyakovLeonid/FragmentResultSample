package com.github.belyakovleonid.fragmentresultsample.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.belyakovleonid.fragmentresultsample.databinding.ListItemBinding
import com.github.belyakovleonid.fragmentresultsample.list.model.ListItemUiModel

class SampleListAdapter : ListAdapter<ListItemUiModel, ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItemUiModel) {
        with(binding) {
            image.setImageResource(item.imageRes)
            text.text = item.name
            textRate.text = item.rate
            textPrice.text = item.price
        }
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<ListItemUiModel>() {
    override fun areItemsTheSame(oldItem: ListItemUiModel, newItem: ListItemUiModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ListItemUiModel, newItem: ListItemUiModel): Boolean {
        return oldItem == newItem
    }
}