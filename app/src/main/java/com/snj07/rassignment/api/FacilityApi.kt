package com.snj07.rassignment.api

import com.snj07.rassignment.utils.global.Constants
import io.reactivex.Single
import retrofit2.http.GET


interface FacilityApi {

    @GET(Constants.DATA_URL)
    fun getData(): Single<BaseResponse>

}