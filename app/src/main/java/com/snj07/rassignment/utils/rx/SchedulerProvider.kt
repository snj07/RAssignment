package com.snj07.rassignment.util.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}