package com.myha.petadoption.utils.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesVerticalItemDecoration(private val spaceStart: Int, private val spaceEnd: Int, private val spaceBottom: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spaceStart
        outRect.right = spaceEnd
        outRect.bottom = spaceBottom
//        if(parent.getChildAdapterPosition(view) == 0) {
//            outRect.top = space
//        }
    }

}