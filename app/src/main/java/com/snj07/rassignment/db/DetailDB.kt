package com.snj07.rassignment.db

import com.snj07.rassignment.api.BaseResponse
import com.snj07.rassignment.model.Exclusion
import com.snj07.rassignment.model.ExclusionDetails
import com.snj07.rassignment.model.Facility
import com.snj07.rassignment.model.Option
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.realm.RealmConfiguration
import io.realm.RealmList
import javax.inject.Inject


class DetailDB @Inject constructor(
    private val realmConfiguration: RealmConfiguration
) {

    fun readData(): Flowable<BaseResponse> {

        var facilitiesFlowable = RadiusRealmObservableFactory.fromList(realmConfiguration) {
            it.where(Facility::class.java).findAll()
        }.toFlowable(BackpressureStrategy.BUFFER)

        var exclusionListFlowable = RadiusRealmObservableFactory.fromList(realmConfiguration) {
            it.where(ExclusionDetails::class.java).findAll()
        }.toFlowable(BackpressureStrategy.BUFFER)
        return Flowable.zip(facilitiesFlowable, exclusionListFlowable, BiFunction { facilities, exclusionLists ->

            val exclusionListList = ArrayList<ArrayList<Exclusion>>()
            var facilityList = facilities


            for (i in exclusionLists) {
                exclusionListList.add(ArrayList((i.exclusionRealmList).toList()))
            }
            val baseResponse = BaseResponse()
            baseResponse.exclusionList = exclusionListList
            baseResponse.facilityList = facilityList
            baseResponse
        })
    }

    fun insertData(
        baseResponse: BaseResponse
    ) {

        if (baseResponse.facilityList != null && !baseResponse.facilityList.isEmpty() &&
            baseResponse.exclusionList != null && !baseResponse.exclusionList.isEmpty()
        ) {
            // clean the existing data
            DBTransaction.execute(realmConfiguration) {
                it.where(Facility::class.java).findAll().deleteAllFromRealm()
            }
            DBTransaction.execute(realmConfiguration) {
                it.where(Option::class.java).findAll().deleteAllFromRealm()
            }
            DBTransaction.execute(realmConfiguration) {
                it.where(ExclusionDetails::class.java).findAll().deleteAllFromRealm()
            }
            DBTransaction.execute(realmConfiguration) {
                it.where(Exclusion::class.java).findAll().deleteAllFromRealm()
            }
            // store facilities and options data
            for (i in baseResponse.facilityList) {
                for (o in i.options) {
                    o.facilityId = i.facilityId
                }
            }
            DBTransaction.execute(realmConfiguration) {
                it.insertOrUpdate(baseResponse.facilityList)
            }

            var ll = ArrayList<ExclusionDetails>()
            // store exclusion list data
            for (exclusionList in baseResponse.exclusionList) {
                val exclusionListList = ExclusionDetails()
                val newExclusionList = RealmList<Exclusion>()
                newExclusionList.addAll(exclusionList)
                exclusionListList.exclusionRealmList = newExclusionList
                ll.add(exclusionListList)
            }
            DBTransaction.execute(realmConfiguration) {
                it.insert(ll)
            }
        }
    }

}