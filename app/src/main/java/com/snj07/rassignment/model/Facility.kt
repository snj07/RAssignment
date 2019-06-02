package com.snj07.rassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Facility : RealmObject() {
    @PrimaryKey
    @SerializedName("facility_id")
    @Expose
    var facilityId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("options")
    @Expose
    var options = RealmList<Option>()


    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Facility ) {
            return false
        }
        return other.facilityId == this.facilityId
    }


    override fun hashCode(): Int {
        return this.facilityId.hashCode()
    }
}
