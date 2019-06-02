package com.snj07.rassignment.service

import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import com.snj07.rassignment.db.DetailDB
import com.snj07.rassignment.repository.FacilityRepository
import com.snj07.rassignment.util.rx.SchedulerProvider
import com.snj07.rassignment.utils.InternetConnectivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class CustomJobServices : JobService() {


    @Inject
    lateinit var detailDB: DetailDB

    @Inject
    lateinit var facilityRepository: FacilityRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    override fun onStopJob(job: JobParameters): Boolean {
        mCompositeDisposable.clear()
        return false
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    private val mCompositeDisposable = CompositeDisposable()
    override fun onStartJob(job: JobParameters): Boolean {
        InternetConnectivity.init(this.applicationContext)
        if (InternetConnectivity.isConnected()) {
            facilityRepository.getData()
                .observeOn(schedulerProvider.io())
                .subscribeBy(
                    onSuccess = {
                        detailDB.insertData(baseResponse = it)
                    }
                )
        }
        return false
    }


}