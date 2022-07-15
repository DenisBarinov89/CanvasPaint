package com.example.canvaspaint

import android.graphics.PorterDuff
import android.widget.ImageView
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


//fun colorAdapterDelegate(onClick: (Int) -> Unit): AdapterDelegate<List<Item>> =
//    adapterDelegateLayoutContainer<ToolItem.ColorModel, Item>(R.layout.item_palette) {
//
//        val color: ImageView = findViewById(R.id.ivColor)
//        itemView.setOnClickListener { onClick(adapterPosition) }
//
//        bind {
//            color.setColorFilter(
//                context.resources.getColor(item.color, null),
//                PorterDuff.Mode.SRC_IN
//            )
//        }
//    }
//
//fun toolsAdapterDelegate(onToolsClick: (Int) -> Unit): AdapterDelegate<List<Item>> =
//    adapterDelegateLayoutContainer<ToolItem.ToolModel, Item>(R.layout.item_tools) {
//
//        val tool: ImageView = findViewById(R.id.ivTool)
//
//        bind {
//            tool.setImageResource(item.type.value)
//            when (item.type) {
//                TOOLS.PALETTE -> {
//                    tool.setColorFilter(
//                        context.resources.getColor(item.selectedColor.value, null),
//                        PorterDuff.Mode.SRC_IN
//                    )
//                }
//                else -> {}
//            }
//        }
//        itemView.setOnClickListener { onToolsClick(adapterPosition) }
//    }