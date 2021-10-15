package com.myha.petadoption.utils.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesHorizontalItemDecoration(private val spaceTop: Int, private val spaceEnd: Int, private val spaceBottom: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = spaceTop
        outRect.right = spaceEnd
        outRect.bottom = spaceBottom
//        if(parent.getChildAdapterPosition(view) == 0) {
//            outRect.left = space
//        }
    }

}