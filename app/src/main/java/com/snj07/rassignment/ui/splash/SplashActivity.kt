package com.snj07.rassignment.ui.splash

import android.os.Bundle
import com.firebase.jobdispatcher.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.snj07.rassignment.R
import com.snj07.rassignment.databinding.ActivitySplashBinding
import com.snj07.rassignment.service.CustomJobServices
import com.snj07.rassignment.ui.base.BaseActivity
import com.snj07.rassignment.utils.Navigator
import com.snj07.rassignment.utils.global.Constants
import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashContract.SplashViewModel>() {


    override fun getLayoutRes() = R.layout.activity_splash
    override fun getModelClass() = SplashContract.SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.inputs = viewModel
        scheduleJob()
        bindObservable()
    }


    private fun bindObservable() {
        bind(
            Completable.timer(2, TimeUnit.SECONDS)
                .observeOn(schedulerProvider.ui())
                .subscribeBy {
                    Navigator.navigateToMainActivity(this)
                    finish()
                }
        )
    }

    private fun scheduleJob() {
        if (isGooglePlayServicesAvailable()) {
            // create a firebase job dispatcher instance
            val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(applicationContext))

            val job = dispatcher.newJobBuilder()
                .setService(CustomJobServices::class.java)
                .setTag(Constants.SYNC_TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                // start between 1 day (24 hours - 86400 seconds) and 1 day 1 hour (90000) seconds from now
                .setTrigger(Trigger.executionWindow(Constants.SERVICE_WINDOW_START, Constants.SERVICE_WINDOW_START))
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build()

            dispatcher.schedule(job)

        }
    }

    private fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(applicationContext)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode, Constants.PLAY_SERVICES_CHECK)
                    .show()
            }
//            showPSUnavailableMessage()
            return false
        }
        return true
    }


}
