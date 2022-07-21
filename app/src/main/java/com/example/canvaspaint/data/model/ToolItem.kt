package com.example.canvaspaint.data.model

import androidx.annotation.ColorRes
import com.example.canvaspaint.data.COLOR
import com.example.canvaspaint.data.SIZE
import com.example.canvaspaint.data.TOOLS

sealed class ToolItem : Item {
    data class ColorModel(@ColorRes val color: Int) : ToolItem()
    data class ToolModel(
        val type: TOOLS,
        val selectedTool: TOOLS = TOOLS.NORMAL,
        val selectedColor: COLOR = COLOR.BLACK,
        val selectedSIZE: SIZE = SIZE.SMALL,
        val isSelected: Boolean = false
    ) : ToolItem()

    data class SizeModel(
        val size: Int
    ) : ToolItem()
}