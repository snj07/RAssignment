package com.snj07.rassignment.ui.base

import android.content.Intent
import androidx.annotation.CheckResult
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.snj07.rassignment.utils.RequestCode
import com.snj07.rassignment.utils.ResultCode
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val intentRelay = PublishRelay.create<Intent>()
    private val activityResultRelay = PublishRelay.create<Triple<Int, Int, Intent?>>()

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCleared() {
        compositeDisposable.clear()

        super.onCleared()
    }

    fun intent(intent: Intent) {
        intentRelay.accept(intent)
    }

    fun activityResult(requestCode: RequestCode, resultCode: ResultCode, data: Intent?) {
        activityResultRelay.accept(Triple(requestCode, resultCode, data))
    }

    @CheckResult
    fun intent(): Observable<Intent> = intentRelay

    @CheckResult
    fun activityResult(): Observable<Triple<RequestCode, ResultCode, Intent?>> = activityResultRelay

    fun bind(vararg disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

}