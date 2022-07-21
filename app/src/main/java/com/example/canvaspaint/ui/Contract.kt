package com.example.canvaspaint.ui

import com.example.canvaspaint.base.Event
import com.example.canvaspaint.data.COLOR
import com.example.canvaspaint.data.TOOLS
import com.example.canvaspaint.data.model.ToolItem

data class ViewState(
    val toolsList: List<ToolItem.ToolModel>,
    val colorList: List<ToolItem.ColorModel>,
    val sizeList: List<ToolItem.SizeModel>,
    val canvasViewState: CanvasViewState,
    val isPaletteVisible: Boolean,
    val isBrushSizeChangerVisible: Boolean,
    val isToolsVisible: Boolean
)

sealed class UiEvent() : Event {
    object OnToolbarClicked : UiEvent()
    data class OnToolsClick(val index: Int) : UiEvent()
    data class OnPaletteClicked(val index: Int) : UiEvent()
    data class OnSizeClick(val index: Int) : UiEvent()
}

sealed class DataEvent() : Event {
    data class OnSetDefaultTools(val tool: TOOLS, val color: COLOR) : DataEvent()
}