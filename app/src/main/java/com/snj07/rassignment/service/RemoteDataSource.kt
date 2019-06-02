package com.snj07.rassignment.service

import com.snj07.rassignment.api.BaseResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getData(): Single<BaseResponse>
}
