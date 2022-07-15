package com.example.canvaspaint

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canvaspaint.base.setData
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class ToolsLayout @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attr, defStyleAttr) {

    private var onClick: (Int) -> Unit = {}

    private val toolsList: RecyclerView by lazy { findViewById(R.id.rvTools) }

    private val adapter = ListDelegationAdapter(
        colorAdapterDelegate { onClick(it) }
//        toolsAdapterDelegate { onClick(it) }
    )

//    private val adapterDelegate = ListDelegationAdapter(
//        colorAdapterDelegate {
//            onClick(it)
//        },
//        sizeChangeAdapterDelegate {
//            onClick(it)
//        },
//        toolsAdapterDelegate {
//            onClick(it)
//        }
//    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        toolsList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        toolsList.setAdapterAndCleanupOnDetachFromWindow(adapterDelegate)
    }

    fun render(list: List<ToolItem>) {
        adapter.setData(list)
    }

    fun setOnClickListener(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }
}