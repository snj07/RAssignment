package com.snj07.rassignment.repository

import com.snj07.rassignment.api.BaseResponse
import com.snj07.rassignment.service.RemoteDataSourceImpl
import io.reactivex.Single
import javax.inject.Inject

class FacilityRepository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl

) {
    fun getData(): Single<BaseResponse> {
        return remoteDataSourceImpl.getData()
    }
}