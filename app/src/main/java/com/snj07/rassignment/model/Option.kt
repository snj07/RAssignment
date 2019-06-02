package com.snj07.rassignment.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.Ignore
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey


open class Option : RealmObject() {



    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: String? = null



    var facilityId: String? = null
    @Ignore
    var isSelected: Boolean = false

    @Ignore
    var isEnabled: Boolean = true

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Option) {
            return false
        }
        return other.id == this.id && other.facilityId ==this.facilityId
    }


    override fun hashCode(): Int {
        return this.id.hashCode()
    }

}