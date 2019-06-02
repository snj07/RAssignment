package com.snj07.rassignment.ui.main.facility

import com.snj07.rassignment.api.BaseResponse
import com.snj07.rassignment.db.DetailDB
import com.snj07.rassignment.model.Option
import com.snj07.rassignment.repository.FacilityRepository
import com.snj07.rassignment.ui.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

interface FaciityContract {
    interface Inputs {
    }

    interface Outputs {
        fun settingItemList(): Single<BaseResponse>
        fun getDbData(): Flowable<BaseResponse>

    }
    interface CustomClickListenerInterface {
        fun onClickOption(option: Option, isHandled: Boolean)
    }
    class FacilityViewModel @Inject constructor(
        private val facilityRepository: FacilityRepository,
        private val db: DetailDB

    ) : BaseViewModel(), FaciityContract.Inputs, FaciityContract.Outputs {

        override fun getDbData(): Flowable<BaseResponse> {
            return db.readData()
        }

        override fun settingItemList(): Single<BaseResponse> {
            return facilityRepository.getData()

        }

        val inputs: FaciityContract.Inputs = this
        val outputs: FaciityContract.Outputs = this


    }
}