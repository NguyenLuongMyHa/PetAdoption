package com.myha.petadoption.utils.extension

import android.view.View

/**
 * Created by Aria on 9/13/2021.
 */
object ViewExtension {
    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }
    fun View.isShow(isShow: Boolean) = if(isShow) show() else hide()

    fun View.isHide(isHide: Boolean) = if(isHide) hide() else show()

    fun View.isGone(isGone: Boolean) = if(isGone) gone() else show()

    fun View.enable(isEnable: Boolean) {
        isEnabled = isEnable
        alpha = if (isEnable) 1.0f
        else 0.5f
    }
}