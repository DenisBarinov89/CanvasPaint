package com.example.canvaspaint.ui

import com.example.canvaspaint.base.BaseViewModel
import com.example.canvaspaint.base.Event
import com.example.canvaspaint.data.COLOR
import com.example.canvaspaint.data.SIZE
import com.example.canvaspaint.data.TOOLS
import com.example.canvaspaint.data.model.ToolItem

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },
        sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
        isPaletteVisible = false,
        isToolsVisible = false,
        isBrushSizeChangerVisible = false,
        canvasViewState = CanvasViewState(
            color = COLOR.BLACK,
            size = SIZE.SMALL,
            tools = TOOLS.NORMAL
        )
    )

    init {
       processDataEvent(DataEvent.OnSetDefaultTools(TOOLS.NORMAL, COLOR.BLACK))
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible,
                    isPaletteVisible = false,
                    isBrushSizeChangerVisible = false
                )
            }
            is UiEvent.OnToolsClick -> {
                when (event.index) {
                    TOOLS.PALETTE.ordinal -> {
                        return previousState.copy(isPaletteVisible = !previousState.isPaletteVisible, isBrushSizeChangerVisible = false)
                    }
                    TOOLS.SIZE.ordinal -> {
                        return previousState.copy(isBrushSizeChangerVisible = !previousState.isBrushSizeChangerVisible, isPaletteVisible = false)
                    }
                    else -> {
                        val toolsList = previousState.toolsList.mapIndexed() { index, model ->
                            if (index == event.index) {
                                model.copy(isSelected = true)
                            } else {
                                model.copy(isSelected = false)
                            }
                        }
                        return previousState.copy(
                            toolsList = toolsList,
                            canvasViewState = previousState.canvasViewState.copy(tools = TOOLS.values()[event.index])
                        )
                    }
                }
            }
            is DataEvent.OnSetDefaultTools -> {
                val toolsList = previousState.toolsList.map { model ->
                    if (model.type == event.tool) {
                        model.copy(isSelected = true, selectedColor = event.color)
                    } else {
                        model.copy(isSelected = false)
                    }
                }
                return previousState.copy(toolsList = toolsList)
            }
            is UiEvent.OnPaletteClicked -> {
                val selectedColor = COLOR.from(event.index)
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.PALETTE) {
                        it.copy(selectedColor = selectedColor)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    canvasViewState = previousState.canvasViewState.copy(color = selectedColor)
                )
            }
            is UiEvent.OnSizeClick -> {
                val selectedSize = SIZE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SIZE) {
                        it.copy(selectedSIZE = selectedSize)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    canvasViewState = previousState.canvasViewState.copy(size = selectedSize))
            }
            else -> return null
        }
    }
}