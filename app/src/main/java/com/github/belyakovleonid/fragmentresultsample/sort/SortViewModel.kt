package com.github.belyakovleonid.fragmentresultsample.sort

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.belyakovleonid.fragmentresultsample.sort.model.SortItemUiModel
import com.github.belyakovleonid.fragmentresultsample.sort.model.SortModel
import com.github.belyakovleonid.fragmentresultsample.sort.model.toUi

class SortViewModel : ViewModel() {

    var currentSort = SortModel.DEFAULT
        private set(value) {
            _sorts.value = availableSorts.map { it.toUi(value) }
            field = value
        }

    private val _sorts = MutableLiveData<List<SortItemUiModel>>()
    val sorts: LiveData<List<SortItemUiModel>> = _sorts

    private val availableSorts = listOf(
        SortModel.PRICE_ASC,
        SortModel.PRICE_DESC,
        SortModel.RATE_ASC,
        SortModel.RATE_DESC,
        SortModel.DEFAULT
    )

    fun onSortSelected(sort: SortItemUiModel) {
        currentSort = sort.value
    }

    fun setSelectedSort(sortModel: SortModel) {
        currentSort = sortModel
    }
}