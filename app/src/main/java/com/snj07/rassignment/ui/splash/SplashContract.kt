package com.snj07.rassignment.ui.splash

import com.snj07.rassignment.api.BaseResponse
import com.snj07.rassignment.repository.FacilityRepository
import com.snj07.rassignment.ui.base.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

interface SplashContract {

    interface Inputs

    interface Outputs {
        fun settingItemList(): Single<BaseResponse>
    }

    class SplashViewModel @Inject constructor(
        private val facilityRepository: FacilityRepository

    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        override fun settingItemList(): Single<BaseResponse> {
            return facilityRepository.getData()

        }
    }

}
