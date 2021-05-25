package com.github.belyakovleonid.fragmentresultsample.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.belyakovleonid.fragmentresultsample.R
import com.github.belyakovleonid.fragmentresultsample.list.model.ListItemUiModel
import com.github.belyakovleonid.fragmentresultsample.sort.model.SortModel

class ListViewModel : ViewModel() {
    private val content = listOf(
        ListItemUiModel(R.drawable.ic_burger, "Burger", "3.9", "3.00$"),
        ListItemUiModel(R.drawable.ic_carrot, "Carrot", "5.0", "2.15$"),
        ListItemUiModel(R.drawable.ic_chicken, "Chicken", "4.5", "1.50$"),
        ListItemUiModel(R.drawable.ic_cupcake, "Cupcake", "4.1", "4.50$"),
        ListItemUiModel(R.drawable.ic_donut, "Donut", "4.4", "0.99$"),
        ListItemUiModel(R.drawable.ic_pizza, "Pizza", "3.9", "5.45$"),
        ListItemUiModel(R.drawable.ic_popcorn, "Popcorn", "4.9", "2.80$"),
        ListItemUiModel(R.drawable.ic_sandwich, "Sandwich", "2.7", "1.80$"),
        ListItemUiModel(R.drawable.ic_macarones, "Macaroni", "4.7", "2.99$"),
        ListItemUiModel(R.drawable.ic_french_fries, "French fries", "4.2", "1.25$"),
    )

    private val selectedSort = MutableLiveData(SortModel.DEFAULT)
    val items: LiveData<List<ListItemUiModel>> = Transformations.map(selectedSort) { sort ->
        getSortedContent(sort)
    }
    val hasSort: LiveData<Boolean> = Transformations.map(selectedSort) { sort ->
        sort != SortModel.DEFAULT
    }

    private fun getSortedContent(sort: SortModel): List<ListItemUiModel> {
        return when (sort) {
            SortModel.PRICE_ASC -> content.sortedBy { it.price }
            SortModel.PRICE_DESC -> content.sortedByDescending { it.price }
            SortModel.RATE_ASC -> content.sortedBy { it.rate }
            SortModel.RATE_DESC -> content.sortedByDescending { it.rate }
            SortModel.DEFAULT -> content
        }
    }

    fun getSelectedSort(): SortModel {
        return selectedSort.value ?: SortModel.DEFAULT
    }

    fun setSelectedSort(sort: SortModel) {
        selectedSort.value = sort
    }
}