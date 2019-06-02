package com.snj07.rassignment.views


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.snj07.rassignment.R

class CustomButton(
    private var mContext: Context, attrs: AttributeSet?, defStyleAttr: Int
) : AppCompatTextView(mContext, attrs, defStyleAttr) {

    @get:DrawableRes
    var selectedBackground = R.drawable.custom_btn_selected_background
    @get:DrawableRes
    var unselectedBackground = R.drawable.custom_btn_unselected_background
    @get:ColorRes
    var selectedTextColor = android.R.color.white
    @get:ColorRes
    var unselectedTextColor = android.R.color.black
    private var isSelected = false

    var isToggleEnabled = true

    private var mClickListener: OnClickListener? = null

    constructor(context: Context) : this(context, null) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        mContext = context
    }

    init {
        init(mContext, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        initAttributes(context, attrs, defStyleAttr)
        changeBackgroundAndTextColor()
        super.setOnClickListener { v ->
            this.setSelected(!isSelected)

            if (mClickListener != null) {
                mClickListener!!.onClick(this@CustomButton)
            }
        }
    }


    private fun initAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyleAttr, 0)
            selectedBackground = array.getResourceId(R.styleable.CustomButton_selectedBackground, selectedBackground)
            unselectedBackground =
                array.getResourceId(R.styleable.CustomButton_unselectedBackground, unselectedBackground)
            selectedTextColor = array.getResourceId(R.styleable.CustomButton_selectedTextColor, selectedTextColor)
            unselectedTextColor = array.getResourceId(R.styleable.CustomButton_unselectedTextColor, unselectedTextColor)
            isSelected = array.getBoolean(R.styleable.CustomButton_selected, isSelected)
            isToggleEnabled = array.getBoolean(R.styleable.CustomButton_isToggleable, isToggleEnabled)
            val fontName = array.getString(R.styleable.CustomButton_cbfont)
            setFontName(context, fontName)
            array.recycle()
        }
    }

    override fun setOnClickListener(listener: OnClickListener) {
        mClickListener = listener
    }

    private fun setFontName(context: Context, fontName: String?) {
        checkNotNull(context)
        try {
            if (fontName != null) {
                val myTypeface = Typeface.createFromAsset(context.assets, "fonts/$fontName")
                setTypeface(myTypeface)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun resetButton() {
        checkNotNull(mContext)
        this.isToggleEnabled = true
        this.setSelected(false)
        super.setBackground(ContextCompat.getDrawable(mContext, unselectedBackground))
        super.setTextColor(ContextCompat.getColor(mContext, unselectedTextColor))
    }

    private fun changeBackgroundAndTextColor() {
        checkNotNull(mContext)
        if (isSelected) {
            super.setBackground(ContextCompat.getDrawable(mContext, selectedBackground))
            super.setTextColor(ContextCompat.getColor(mContext, selectedTextColor))
        } else {
            super.setBackground(ContextCompat.getDrawable(mContext, unselectedBackground))
            super.setTextColor(ContextCompat.getColor(mContext, unselectedTextColor))
        }
    }

    override fun isSelected(): Boolean {
        return isSelected
    }

    override fun setSelected(isSelected: Boolean) {
        if (isToggleEnabled) {
            this.isSelected = isSelected
            this.changeBackgroundAndTextColor()
        }
    }
}