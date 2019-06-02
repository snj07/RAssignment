package com.snj07.rassignment.ui.main.facility

import android.content.Context
import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snj07.rassignment.databinding.ItemOptionBinding
import com.snj07.rassignment.model.Facility
import com.snj07.rassignment.model.Option
import com.snj07.rassignment.ui.main.setting.OptionViewHolder
import com.snj07.rassignment.utils.extension.inflater

class OptionRecyclerViewAdapter(
    optionList: List<Option>,
    context: Context,
    customClickListenerInterface: FaciityContract.CustomClickListenerInterface
) :
    RecyclerView.Adapter<OptionViewHolder>() {
    var context: Context = context
    var customClickListenerInterface = customClickListenerInterface

    private var optionList: List<Option>? = optionList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder(ItemOptionBinding.inflate(parent.inflater(), parent, false))
    }

    override fun getItemCount(): Int {
        if (optionList == null) {
            return 0
        }
        return optionList?.size!!
    }


    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {

        val option = optionList!![position] ?: return

        if (option?.name != null && !TextUtils.isEmpty(option.name) &&
            option.icon != null && !TextUtils.isEmpty(option.icon)
        ) {
            // set the name of the facility
            holder.bind(option, context, customClickListenerInterface)

            // setup recycler view for chips
//            setupOptionsRecyclerView(viewHolder.mOptionRecyclerView, currentFacility!!)
        } else {
            // reset the item
//            holder.bind(FacilityListItems.FacilityItems(id = "", facilityName = ""))
//            viewHolder.mFacilityName.setText("")
//            viewHolder.itemView.setVisibility(View.GONE)
        }
    }

    fun updateList(it: List<Option>?) {
        this.optionList = it
        this.notifyDataSetChanged()
    }

    fun configureOptionRB(optionRecyclerView: RecyclerView, currentFacility: Facility) {

    }

}