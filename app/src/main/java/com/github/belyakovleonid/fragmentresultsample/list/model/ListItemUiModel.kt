package com.github.belyakovleonid.fragmentresultsample.list.model

import androidx.annotation.DrawableRes

data class ListItemUiModel(
    @DrawableRes val imageRes: Int,
    val name: String,
    val rate: String,
    val price: String
)