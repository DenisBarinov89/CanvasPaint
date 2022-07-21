package com.example.canvaspaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.canvaspaint.ui.CanvasViewModel
import com.example.canvaspaint.ui.UiEvent
import com.example.canvaspaint.ui.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PALETTE = 0
        private const val TOOLS = 1
        private const val SIZE = 2
    }

    private val viewModel: CanvasViewModel by viewModel()
    private var toolsList: List<ToolsLayout> = listOf()
    private val paletteLayout: ToolsLayout by lazy { findViewById(R.id.paletteLayout) }
    private val toolsLayout: ToolsLayout by lazy { findViewById(R.id.toolLayout) }
    private val sizeLayout: ToolsLayout by lazy { findViewById(R.id.sizeLayout) }
    private val toolsMenu: ImageView by lazy { findViewById(R.id.ivMenu) }
    private val clearButton: ImageView by lazy { findViewById(R.id.ivClear) }
    private val drawView: DrawView by lazy { findViewById(R.id.viewDraw) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolsList = listOf(paletteLayout, toolsLayout, sizeLayout)
        viewModel.viewState.observe(this,::render)
        toolsMenu.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnToolbarClicked)
        }
        clearButton.setOnClickListener {
            drawView.clear()
        }

        toolsLayout.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnToolsClick(it))
        }
        paletteLayout.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnPaletteClicked(it))
        }
        sizeLayout.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnSizeClick(it))
        }


    }

    private fun render(viewState: ViewState) {
        with(toolsList[PALETTE]) {
            render(viewState.colorList)
            isVisible = viewState.isPaletteVisible
        }

        with(toolsList[TOOLS]) {
            render(viewState.toolsList)
            isVisible = viewState.isToolsVisible
        }

        with(toolsList[SIZE]) {
            render(viewState.sizeList)
            isVisible = viewState.isBrushSizeChangerVisible
        }
        drawView.render(viewState.canvasViewState)
    }
}