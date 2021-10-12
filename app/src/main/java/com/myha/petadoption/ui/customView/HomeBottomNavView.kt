package com.myha.petadoption.ui.customView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.ViewPager
import com.myha.petadoption.R
import com.myha.petadoption.databinding.CustomHomeBottomNavViewBinding

class HomeBottomNavView @JvmOverloads constructor(
    @NonNull context: Context,
    @Nullable attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    var selectedPosition = 0
    private var binding: CustomHomeBottomNavViewBinding
    private var tabsIcons: Array<ImageView>
    private var tabsIconsDrawableFill: Array<Int>
    private var tabsIconsDrawableOutline: Array<Int>
    private var tabsLayout: Array<ViewGroup>
    var onTabSelected: OnTabSelected? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding =
            DataBindingUtil.inflate(inflater, R.layout.custom_home_bottom_nav_view, this, true)
        tabsIcons =
            arrayOf(binding.ivHome, binding.ivLocation, binding.ivExplore, binding.ivDiscuss)
        tabsIconsDrawableFill = arrayOf(
            R.drawable.ic_home_fill,
            R.drawable.ic_location_fill,
            R.drawable.ic_explore_fill,
            R.drawable.ic_discuss_fill
        )
        tabsIconsDrawableOutline = arrayOf(
            R.drawable.ic_home_outline,
            R.drawable.ic_location_outline,
            R.drawable.ic_explore_outline,
            R.drawable.ic_discuss_outline
        )
        tabsLayout = arrayOf(
            binding.layoutHome,
            binding.layoutLocation,
            binding.layoutExplore,
            binding.layoutDiscuss
        )

        val attrArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.HomeBottomNavView,
            defStyleAttr,
            defStyleRes
        )
        selectedPosition =
            attrArray.getInt(R.styleable.HomeBottomNavView_selected, HomeBottomItem.HOME.value)
        attrArray.recycle()
        setDrawableCurrentTab()
        for ((position, tab) in tabsLayout.withIndex()) {
            tab.setOnClickListener {
                if (selectedPosition != position) {
                    onTabSelected?.onTabSelected(position)
                }
                selectedPosition = position
                setDrawableCurrentTab()
            }
        }

    }

    private fun setDrawableCurrentTab() {
        tabsIcons.mapIndexed { index: Int, imageView: ImageView ->
            if (index != selectedPosition) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        tabsIconsDrawableOutline[index]
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        tabsIconsDrawableFill[index]
                    )
                )
            }
        }
    }

    fun setCurrentTab(position: Int, viewPager: ViewPager) {
        selectedPosition = position
        viewPager.currentItem = selectedPosition
        setDrawableCurrentTab()
    }


    enum class HomeBottomItem(val value: Int) {
        HOME(0), LOCATION(1), EXPLORE(2), DISCUSS(3)
    }

    interface OnTabSelected {
        fun onTabSelected(position: Int)
    }

}