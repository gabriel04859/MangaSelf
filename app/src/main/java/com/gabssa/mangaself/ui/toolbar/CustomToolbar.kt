package com.gabssa.mangaself.ui.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gabssa.mangaself.databinding.CustomToolbarLayoutBinding
import com.gabssa.mangaself.R
import com.gabssa.mangaself.extensions.isVisible

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: CustomToolbarLayoutBinding

    init {
        binding = CustomToolbarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        initViews()
    }

    private fun initViews() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, defStyleAttr, 0)

        try {
            val title = typedArray.getString(R.styleable.CustomToolbar_toolbar_title) ?: ""
            val isLeftIconVisible = typedArray.getBoolean(R.styleable.CustomToolbar_toolbar_is_left_icon_visible, false)
            val isRightIconVisible = typedArray.getBoolean(R.styleable.CustomToolbar_toolbar_is_right_icon_visible, false)

            setValuesToView(title, isLeftIconVisible, isRightIconVisible)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setValuesToView(
        title: String,
        isLeftIconVisible: Boolean,
        isRightIconVisible: Boolean
    ) {
        with(binding) {
            titleToolbar.text = title
            buttonLeft.isVisible(isLeftIconVisible)
            buttonRight.isVisible(isRightIconVisible)
        }
    }

    fun leftIconClickListener(action: () -> Unit) {
        binding.buttonLeft.setOnClickListener {
            action.invoke()
        }
    }

    fun rightIconClickListener(action: () -> Unit) {
        binding.buttonRight.setOnClickListener {
            action.invoke()
        }
    }
}