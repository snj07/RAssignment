package com.snj07.rassignment.service

import com.snj07.rassignment.api.BaseResponse
import com.snj07.rassignment.api.FacilityApi
import com.snj07.rassignment.util.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val facilityApi: FacilityApi,
    private val schedulerProvider: SchedulerProvider

) : RemoteDataSource {
    override fun getData(): Single<BaseResponse> {
        return facilityApi.getData().subscribeOn(schedulerProvider.io())
    }
}