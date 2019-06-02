package com.snj07.rassignment.db

import com.snj07.rassignment.model.Exclusion
import com.snj07.rassignment.model.ExclusionDetails
import com.snj07.rassignment.model.Facility
import com.snj07.rassignment.model.Option
import io.realm.annotations.RealmModule

@RealmModule(
    classes = [
        Facility::class, Exclusion::class, ExclusionDetails::class, Option::class
    ]
)
class RadiusRealmScheme