package com.github.belyakovleonid.fragmentresultsample.sort.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.belyakovleonid.fragmentresultsample.databinding.SortItemBinding
import com.github.belyakovleonid.fragmentresultsample.sort.model.SortItemUiModel

class SortAdapter(
    private val onSortSelected: (SortItemUiModel) -> Unit
) : ListAdapter<SortItemUiModel, ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SortItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), onSortSelected)
    }
}

class ItemViewHolder(
    private val binding: SortItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: SortItemUiModel,
        onSortSelected: (SortItemUiModel) -> Unit
    ) = with(binding.button) {
        setOnClickListener { onSortSelected.invoke(item) }
        setText(item.textResId)
        isChecked = item.isSelected
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<SortItemUiModel>() {
    override fun areItemsTheSame(oldItem: SortItemUiModel, newItem: SortItemUiModel): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: SortItemUiModel, newItem: SortItemUiModel): Boolean {
        return oldItem == newItem
    }
}