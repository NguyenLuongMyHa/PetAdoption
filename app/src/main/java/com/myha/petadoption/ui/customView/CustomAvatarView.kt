package com.myha.petadoption.ui.customView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.myha.petadoption.R
import com.myha.petadoption.databinding.CustomAvatarViewBinding
import com.myha.petadoption.utils.extension.ViewExtension.isShow

/**
 * Created by Aria on 9/13/2021.
 */
class CustomAvatarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {
    private var binding: CustomAvatarViewBinding
    private var hasFollow = false
        set(value) {
            field = value
            binding.ivFollowing.isShow(value)
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.custom_avatar_view, this, true)

        val attrArray: TypedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.CustomAvatarView, defStyleAttr, defStyleRes
        )
        hasFollow = attrArray.getBoolean(R.styleable.CustomAvatarView_hasFollow, false)
        binding.strokeColorRes = attrArray.getColor(R.styleable.CustomAvatarView_strokeColor, 0)
        binding.strokeWidth = attrArray.getDimension(R.styleable.CustomAvatarView_strokeWidth, 0F)
        binding.iconFollowRes = attrArray.getResourceId(R.styleable.CustomAvatarView_icFollowRes, R.drawable.ic_follow)
        attrArray.recycle()
    }

}

/*
NOTE - CUSTOM_VIEW
https://proandroiddev.com/android-custom-view-level-1-67ed1c3febe1
https://proandroiddev.com/android-custom-views-level-2-7a0f110a2ce9
https://medium.com/@dev.tarunsharma/android-custom-view-level-3-81e767c8cc75
LifeCycles:
- onAttachedToWindow() Called when the view is attached to a window.
- measure()
- onMeasure(int, int) Called to determine the size requirements for this view and all of its children. But we don’t use it. we use setMeasuredDimension(800, 300)
- layout()
- onLayout( int, int, int, int) Called when this view should assign a size and position to all of its children.
- draw()
- onDraw(android.graphics.Canvas) Called when the view should render its content. It provides canvas as an argument, we draw anything on canvas using Paint class Instance.
-> Visible to user
if(requestLayout() -> go to measure()) -> When we need to recalculate the specification(height and width) of views. so it will call view life cycle major phase like measure, layout than a draw. so through requestlayout() method changes, we can redraw view bounds.
if(invalidate() -> go to draw()) -> when we need to redraw a particular view mainly appearance perspective. we use to invalidate.

- onDettachToWindow() Called when the view is dettached to a window.
How Android System Draw Activity UI?
- When Activity brings to focus, the Android Framework request activity to provide the root node of its hierarchy(Top View Element in Layout file).
  let us take an example, we have base View Group Component(RelativeLayout) which holds its all children.
  That ViewGroup(RelativeLayout) is the Root Node of Layout which acts as a starting point of Drawing UI on Screen. Android Framework Handles it.
- After the root is provided to the Android Framework. View Group(Root Node ) calls its children in Top-down (BFS)fashion to draw themselves. There are two important phases of View Drawing:
  * At First Phase, Each View tries to calculate its dimensions specifications down the tree(View hierarchy).
  this is called *measure pass*. this phase happens in measure(int, int). At the end of this phase, all Views have their measurement.

  * In the second Phase, this pass happens in layout(int, int, int, int). This is also a top-down approach.
  During this pass, each parent draws their children to there positions. Parent view also used there dimensions specification(which view stored at measure pass phase) to position its children’s views on Screen.

How parents define each child’s View height and width?
- Parent define child height and width using MeasureSpec Class options
  * Unspecified: child can expand its bounds
  * Exactly: a child should be the exact size
  * Atmost: child can expand them to a particular limit

Each View Height and Width preference is defined by 3 ViewGroup.LayoutParams class options:
1. An Exact Number
2. match_parent = View wants to be like a parent
3. wrap_content = View wants to wrap its content

The above process can be repeated multiple times (interaction between view and parent). So measure() can be called more than Once.

After this recursive process, the time comes for the draw. Now Everything is measured and positioned.
The Drawing starts with the parent. then the parent requests children to do the same. So children would be drawn on the top of the parent.

onDraw(Canvas c) is called automatically. We can use it to draw shapes, etc.
draw(Canvas c) is used to manually render custom view (and all of its children) to the given canvas. The view must have already done a full layout before this function is called.

How to set height and width or size of View?
- By Measure pass and layout pass, the Android framework determines the Specification of View.
- By Using super.onMeasure(widthMeasureSpec,heightMeasureSpec)
we can use the implementation of onMeasure() provided by Parents classes. We want to set your specification use setMeasuredDimension(int,int). But before setting specification(height and width) of view, consider padding factor, minimum height and maximum height and width and height specification provide by parent view. if you don’t use super.onMeasure(int,int) and forget to call setMeasuredDimension, the result will be an inflate-exceptions.


onFinishInflate() Called after a view and all of its children have been inflated from XML.

 */