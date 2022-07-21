package com.example.canvaspaint

import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import com.example.canvaspaint.data.model.Item
import com.example.canvaspaint.data.TOOLS
import com.example.canvaspaint.data.model.ToolItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate



fun colorAdapterDelegate(onClick: (Int) -> Unit) =
    adapterDelegate<ToolItem.ColorModel, Item>(R.layout.item_palette) {
        val color: ImageView = findViewById(R.id.ivColor)
        color.setOnClickListener { onClick(item.color) }
        bind {
            color.setColorFilter(
                context.resources.getColor(item.color, null),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

fun sizeAdapterDelegate(onSizeClick: (Int) -> Unit) =
    adapterDelegate<ToolItem.SizeModel, Item>(R.layout.item_size) {
        val size: TextView = findViewById(R.id.tvSize)
        size.setOnClickListener { onSizeClick(absoluteAdapterPosition) }
        bind {
            size.text = item.size.toString()
        }
    }


fun toolsAdapterDelegate(onToolsClick: (Int) -> Unit) =
    adapterDelegate<ToolItem.ToolModel, Item>(R.layout.item_tools) {
        val tool: ImageView = findViewById(R.id.ivTool)
        val currentSize: TextView by lazy { findViewById(R.id.selectedSize) }
        tool.setOnClickListener { onToolsClick(absoluteAdapterPosition) }
        bind {
            tool.setImageResource(item.type.value)
            when (item.type) {
                TOOLS.PALETTE -> {
                    tool.setColorFilter(
                        context.resources.getColor(item.selectedColor.value, null),
                        PorterDuff.Mode.SRC_IN
                    )
                }
                TOOLS.SIZE -> {
                    currentSize.text = item.selectedSIZE.value.toString()
                }
                else -> {
                    if (item.isSelected) {
                        tool.setBackgroundResource(R.drawable.ic_selected_tool)
                    }
                    else {
                        tool.setBackgroundResource(R.color.transparent)
                    }
                }
            }
        }
    }
