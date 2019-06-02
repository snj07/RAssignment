package com.snj07.rassignment.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.snj07.rassignment.model.Exclusion
import com.snj07.rassignment.model.Facility


open class BaseResponse {
    @SerializedName("facilities")
    @Expose
    var facilityList: List<Facility> = ArrayList<Facility>()

    @SerializedName("exclusions")
    @Expose
    var exclusionList: java.util.ArrayList<java.util.ArrayList<Exclusion>> =
        java.util.ArrayList<java.util.ArrayList<Exclusion>>()
}