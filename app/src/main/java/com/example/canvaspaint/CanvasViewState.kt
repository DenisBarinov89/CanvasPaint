package com.example.canvaspaint

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class CanvasViewState(val color: COLOR, val size: SIZE, val tools: TOOLS)

enum class COLOR(
    @ColorRes
    val value: Int
) {

    BLACK(R.color.black),
    RED(R.color.red),
    BLUE(R.color.blue);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}

enum class SIZE(
    val value: Int
) {
    SMALL(4),
    MEDIUM(16),
    LARGE(32);

    companion object {
        private val map = values().associateBy(SIZE::value)
        fun from(size: Int) = map[size] ?: SMALL
    }
}

enum class TOOLS(
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.ic_horizontal_line),
    DASH(R.drawable.ic_dash_line),
    SIZE(R.drawable.ic_size),
    PALETTE(R.drawable.ic_palette)
}