package com.example.canvaspaint

import com.example.canvaspaint.base.BaseViewModel
import com.example.canvaspaint.base.Event

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },
        isPaletteVisible = false,
        isToolsVisible = false
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {

            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(isToolsVisible = !previousState.isToolsVisible, isPaletteVisible = false)
            }

            is UiEvent.OnToolsClick -> {
                if (event.index == TOOLS.PALETTE.ordinal) {
                    return previousState.copy(isPaletteVisible = !previousState.isPaletteVisible)
                }
                return null
            }
            else -> return null
        }


    }
}