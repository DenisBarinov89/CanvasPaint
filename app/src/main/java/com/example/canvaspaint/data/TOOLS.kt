package com.example.canvaspaint.data

import androidx.annotation.DrawableRes
import com.example.canvaspaint.R

enum class TOOLS(

    val value: Int,

) {
    NORMAL(R.drawable.ic_horizontal_line),
    DASH(R.drawable.ic_dash_line),
    PALETTE(R.drawable.ic_palette),
    SIZE(R.drawable.ic_size)
}