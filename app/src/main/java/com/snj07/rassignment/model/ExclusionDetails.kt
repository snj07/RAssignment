package com.snj07.rassignment.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*


@RealmClass
open class ExclusionDetails : RealmObject() {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var exclusionRealmList = RealmList<Exclusion>()
}
