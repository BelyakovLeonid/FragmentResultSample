package com.github.belyakovleonid.fragmentresultsample.sort.model

import androidx.annotation.StringRes

data class SortItemUiModel(
    val value: SortModel,
    @StringRes val textResId: Int,
    val isSelected: Boolean
)

fun SortModel.toUi(selectedSort: SortModel) = SortItemUiModel(
    value = this,
    textResId = textResId,
    isSelected = this == selectedSort
)