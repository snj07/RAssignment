package com.snj07.rassignment.ui.main.facility

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.snj07.rassignment.R
import com.snj07.rassignment.api.BaseResponse
import com.snj07.rassignment.databinding.FragmentFacilityBinding
import com.snj07.rassignment.db.DetailDB
import com.snj07.rassignment.model.Option
import com.snj07.rassignment.repository.FacilityRepository
import com.snj07.rassignment.ui.base.BaseFragment
import com.snj07.rassignment.utils.InternetConnectivity
import com.snj07.rassignment.utils.extension.toast
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_facility.*
import javax.inject.Inject

class FacilityFragment : BaseFragment<FragmentFacilityBinding, FaciityContract.FacilityViewModel>(),
    FaciityContract.CustomClickListenerInterface {

    @Inject
    lateinit var detailDB: DetailDB

    @Inject
    lateinit var facilityRepository: FacilityRepository

    @Inject
    lateinit var facilityAdapter: FacilityRecyclerViewAdapter

    companion object {
        const val TAG = "filter"
        fun newInstance() = FacilityFragment()
        var exclusionSetMap = HashMap<Option, HashSet<Option>>()
        var enabledMain = HashMap<String, Option?>()
    }


    override fun getLayoutRes() = R.layout.fragment_facility
    override fun getModelClass() = FaciityContract.FacilityViewModel::class.java


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        facilityAdapter.setConttext(context!!.applicationContext)
        facilityAdapter.setClickListener(this)
        binding.inputs = viewModel

        initializeRecyclerView()
        bindObservable()
    }

    override fun onClickOption(op: Option, isHandled: Boolean) {

        if (!isHandled && (op.facilityId == "1" || op.facilityId == "2")) {
            validateFacility(op)
        }
        if (exclusionSetMap.containsKey(op)) {
            if (op.isEnabled) {
                if (op.isSelected) {
                    op.isSelected = false
                    var set = exclusionSetMap.get(op)
                    if (set != null) {
                        for (v in set.iterator()) {
                            if (v.equals(op)) {
                                continue
                            }
                            v.isEnabled = true
                            v.isSelected = false
                        }
                    }
                } else {
                    op.isSelected = true
                    var set = exclusionSetMap.get(op)
                    if (set != null) {
                        for (v in set.iterator()) {
                            if (v.equals(op)) {
                                continue
                            }
                            v.isEnabled = false
                            v.isSelected = false
                        }
                    }
                }
            }
        } else {
            op.isSelected = !op.isSelected
        }
        if (!isHandled)
            facilityAdapter.notifyDataSetChanged()
    }

    private fun validateFacility(op: Option) {
        if (op.facilityId == "1" || op.facilityId == "2") {
            if (!op.isSelected) {
                if (enabledMain.containsKey(op.facilityId!!)) {
                    this.onClickOption(enabledMain.get(op.facilityId!!)!!, true)
                    enabledMain.put(op.facilityId!!, op)
                } else {
                    enabledMain.put(op.facilityId!!, op)
                }
            } else {
                if (enabledMain.containsKey(op.facilityId!!)) {
                    enabledMain.remove(op.facilityId!!)
                }
            }
        }
    }

    private fun initializeRecyclerView() {
        facilities_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = facilityAdapter
        }

    }

    private var baseresponse: BaseResponse? = null

    private fun bindObservable() {
        binding.pbVisibility = View.VISIBLE
        bind(

            viewModel.outputs.getDbData()
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                    onNext = {
                        this.baseresponse = it
                        exclusionSetMap.clear()
                        enabledMain.clear()
                        if (this.baseresponse == null || this.baseresponse!!.facilityList.isEmpty()) {
                            handleNoDataInDb()

                        } else {
                            handleExclustionList(this.baseresponse!!)
                            facilityAdapter.updateList(this.baseresponse!!.facilityList)
                            binding.pbVisibility = View.GONE
                            binding.emptyTV = View.GONE
                        }

                    }
                )
        )
    }

    private fun handleNoDataInDb() {
        InternetConnectivity.init(this.context!!.applicationContext)
        if (InternetConnectivity.isConnected()) {
            facilityRepository.getData()
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                    onSuccess = { it2 ->
                        detailDB.insertData(baseResponse = it2)
                    }
                )
        }else{
            context?.applicationContext?.toast(getString(R.string.no_internet_available), Toast.LENGTH_LONG)
            binding.pbVisibility = View.GONE
            binding.emptyTV = View.VISIBLE
        }
    }


    private fun handleExclustionList(it: BaseResponse) {
        for (eList in it.exclusionList) {
            if (eList.size < 2) {
                continue
            }
            var e1 = eList[0]
            var e2 = eList[1]
            var o1 = it.facilityList.filter { f -> f.facilityId == e1.facilityId }.first()
                .options.filter { f -> f.id == e1.optionsId }.first()
            var o2 = it.facilityList.filter { f -> f.facilityId == e2.facilityId }.first()
                .options.filter { f -> f.id == e2.optionsId }.first()
            if (exclusionSetMap.containsKey(o1)) {
                exclusionSetMap.get(o1)!!.add(o2)
            } else {
                var set = HashSet<Option>()
                set.add(o2)
                exclusionSetMap.put(o1, set)
            }
            if (exclusionSetMap.containsKey(o2)) {
                exclusionSetMap.get(o2)!!.add(o1)
            } else {
                var set = HashSet<Option>()
                set.add(o1)
                exclusionSetMap.put(o2, set)
            }
        }
    }

}