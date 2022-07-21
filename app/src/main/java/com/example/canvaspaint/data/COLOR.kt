package com.example.canvaspaint.data

import androidx.annotation.ColorRes
import com.example.canvaspaint.R

enum class COLOR(
    @ColorRes
    val value: Int
) {
    BLACK(R.color.black),
    RED(R.color.red),
    BLUE(R.color.blue),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    ORANGE(R.color.orange);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}