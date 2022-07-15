package com.example.canvaspaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.isVisible
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
    private val toolsMenu: ImageView by lazy { findViewById(R.id.ivMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolsList = listOf(paletteLayout, toolsLayout)
        viewModel.viewState.observe(this,::render)
        paletteLayout.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnPaletteClicked(it))
        }
        toolsLayout.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnToolsClick(it))
        }

        toolsMenu.setOnClickListener {
            viewModel.processUIEvent(UiEvent.OnToolbarClicked)
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
    }
}