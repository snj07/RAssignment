package com.snj07.rassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*


open class Exclusion : RealmObject(), Serializable {

    @SerializedName("facility_id")
    @Expose
    var facilityId: String? = null
    @SerializedName("options_id")
    @Expose
    var optionsId: String? = null

    override fun equals(other: Any?): Boolean {
        if (other !is Exclusion || other == null) {
            return false
        }
        return other.facilityId == this.facilityId || other.optionsId == this.optionsId
    }

    override fun hashCode(): Int {
        return facilityId.hashCode() + optionsId.hashCode()
    }
}
