package com.github.belyakovleonid.fragmentresultsample.sort.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.github.belyakovleonid.fragmentresultsample.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class SortModel(@StringRes val textResId: Int) : Parcelable {
    PRICE_ASC(R.string.sort_price_asc),
    PRICE_DESC(R.string.sort_price_desc),
    RATE_ASC(R.string.sort_rate_asc),
    RATE_DESC(R.string.sort_rate_desc),
    DEFAULT(R.string.sort_default)
}