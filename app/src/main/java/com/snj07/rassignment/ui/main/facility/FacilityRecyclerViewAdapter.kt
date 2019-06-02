package com.snj07.rassignment.ui.main.facility

import android.content.Context
import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snj07.rassignment.databinding.ItemFilterBinding
import com.snj07.rassignment.model.Facility
import com.snj07.rassignment.ui.main.setting.FilterViewHolder
import com.snj07.rassignment.utils.extension.inflater

class FacilityRecyclerViewAdapter :
    RecyclerView.Adapter<FilterViewHolder>() {

    lateinit var context: Context
    lateinit var customClickListenerInterface: FaciityContract.CustomClickListenerInterface
    private var facilityList: List<Facility>? = null

    fun setConttext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(ItemFilterBinding.inflate(parent.inflater(), parent, false))
    }

    override fun getItemCount(): Int {
        if (facilityList == null) {
            return 0
        }
        return facilityList?.size!!
    }


    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {

        val currentFacility = facilityList?.get(position) ?: return

        if (currentFacility != null && currentFacility!!.name != null && !TextUtils.isEmpty(currentFacility!!.name) &&
            currentFacility!!.name != null
        ) {
            // set the name of the facility
            holder.bind(facilityList!![position], context, customClickListenerInterface)

            // setup recycler view for chips
//            setupOptionsRecyclerView(viewHolder.mOptionRecyclerView, currentFacility!!)
        } else {
            // reset the item
            holder.bind(null, context, customClickListenerInterface)
//            viewHolder.mFacilityName.setText("")
//            viewHolder.itemView.setVisibility(View.GONE)
        }
    }

    fun updateList(it: List<Facility>?) {
        this.facilityList = it
        this.notifyDataSetChanged()
    }

    fun configureOptionRB(optionRecyclerView: RecyclerView, currentFacility: Facility) {

    }

    fun setClickListener(click: FaciityContract.CustomClickListenerInterface) {
        customClickListenerInterface = click
    }

}