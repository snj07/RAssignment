package com.snj07.rassignment.ui.main.setting

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.snj07.rassignment.databinding.ItemFacilityBinding
import com.snj07.rassignment.databinding.ItemOptionBinding
import com.snj07.rassignment.model.Facility
import com.snj07.rassignment.model.Option
import com.snj07.rassignment.ui.main.facility.FaciityContract
import com.snj07.rassignment.ui.main.facility.OptionRecyclerViewAdapter
import com.snj07.rassignment.views.CustomButton

class FilterViewHolder(private val binding: ItemFacilityBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var context: Context
    fun bind(
        item: Facility?,
        context: Context,
        customClickListenerInterface: FaciityContract.CustomClickListenerInterface
    ) {
        if (item == null) {
            binding.facilityName.text = ""
            binding.optionRecyclerView.visibility = View.GONE
        } else {
            binding.optionRecyclerView.visibility = View.VISIBLE
        }
        binding.item = item
        this.context = context
        setupOptionsRecyclerView(binding.optionRecyclerView, item!!, customClickListenerInterface)
    }

    //    fun bind(item: String) {
//        binding.facilityName.text = item
//    }
//
//    fun setOnCheckedChnageListener(checkedChangeListener: CompoundButton.OnCheckedChangeListener) {
//        binding.onCheckedChangedListener = checkedChangeListener
//    }
    private fun setupOptionsRecyclerView(
        optionRecyclerView: RecyclerView,
        facility: Facility,
        customClickListenerInterface: FaciityContract.CustomClickListenerInterface
    ) {
        val flexboxLayoutManager = FlexboxLayoutManager(
            context,
            FlexDirection.ROW, FlexWrap.WRAP
        )
        optionRecyclerView.layoutManager = flexboxLayoutManager
        var optionRecyclerViewAdapter =
            OptionRecyclerViewAdapter(facility.options, context, customClickListenerInterface)
        optionRecyclerView.adapter = optionRecyclerViewAdapter
    }
}

class OptionViewHolder(private val binding: ItemOptionBinding) : RecyclerView.ViewHolder(binding.root) {

    var context: Context? = null

    fun bind(
        option: Option,
        context: Context,
        customClickListenerInterface: FaciityContract.CustomClickListenerInterface
    ) {
        this.context = context
        // set option name
        binding.cbutton.text = option.name
        binding.cbutton.setOnClickListener(View.OnClickListener {
            customClickListenerInterface.onClickOption(option,false)
        })
        // set option id

        var iconName = option.icon?.replace("-".toRegex(), "_")
        var iconResource = context.resources.getIdentifier(
            iconName, "drawable",
            context.applicationInfo.packageName
        )
        // add icon
        if (iconResource != 0) {
            binding.cbutton.setCompoundDrawablesWithIntrinsicBounds(iconResource, 0, 0, 0)
            binding.cbutton.compoundDrawablePadding = 8
            if (option.isSelected) {
                // change color of drawable to white
                setTextViewDrawableColor(binding.cbutton, android.R.color.white)
            } else {
                // reset color of drawable
                resetTextViewDrawableColor(binding.cbutton)
            }
        } else {
            // remove any drawables, if exists, to avoid invalid data
            binding.cbutton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            binding.cbutton.setCompoundDrawablePadding(0)
        }
        // set chip selection based on current option's isSelected field
        binding.cbutton.isSelected = option.isSelected
        if (option.isEnabled) {
            binding.cbutton.setAlpha(1.0f)
            binding.cbutton.setEnabled(true)
        } else {
            binding.cbutton.setAlpha(0.3f)
            binding.cbutton.setEnabled(false)
        }
        binding.cbutton.isEnabled = option.isEnabled
//        binding.cbutton.setOnClickListener({ view ->
//            // call the onItemClickListener method to invoke its implementation in its respective activity/fragment
////            mClickListenerInterface.onItemClickListener(currentOption.isSelected(), mFacilityId, currentOption.getId())
//        })
//        binding.item = item
    }

    /**
     * Set custom button drawable colorFilter. It uses [PorterDuffColorFilter] and
     * [Drawable.setColorFilter]
     *
     * @param customButton Custom button which consists drawables
     * @param color        Color id that needs to be added
     */
    private fun setTextViewDrawableColor(customButton: CustomButton, color: Int) {

        for (drawable in customButton.compoundDrawables) {
            if (drawable != null) {
                drawable!!.colorFilter = PorterDuffColorFilter(
                    context!!.resources
                        .getColor(color), PorterDuff.Mode.SRC_IN
                )
            }
        }

    }

    /**
     * Resets custom button drawable colorFilter. It uses [Drawable.clearColorFilter]
     *
     * @param customButton Custom button which consists drawables
     */
    private fun resetTextViewDrawableColor(customButton: CustomButton) {
        for (drawable in customButton.compoundDrawables) {
            if (drawable != null) {
                drawable!!.clearColorFilter()
            }
        }
    }
//    fun bind(item: String) {
//        binding.facilityName.text = item
//    }
//
//    fun setOnCheckedChnageListener(checkedChangeListener: CompoundButton.OnCheckedChangeListener) {
//        binding.onCheckedChangedListener = checkedChangeListener
//    }
}