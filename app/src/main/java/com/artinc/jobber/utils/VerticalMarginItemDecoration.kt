package com.artinc.jobber.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalMarginItemDecoration(
    private val startMargin: Int,
    private val endMargin: Int,
    private val defaultMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return
        val itemCount = parent.adapter?.itemCount ?: 0

        outRect.top = defaultMargin
        outRect.bottom = defaultMargin

        // Устанавливаем отступы для первого элемента
        if (position == 0) {
            outRect.top = startMargin
        }

        // Устанавливаем отступы для последнего элемента
        if (position == itemCount - 1) {
            outRect.bottom = endMargin
        }
    }
}
