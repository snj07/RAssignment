package com.snj07.rassignment.di

import androidx.lifecycle.ViewModel
import com.snj07.rassignment.di.annotation.ViewModelKey
import com.snj07.rassignment.ui.main.MainContract
import com.snj07.rassignment.ui.main.facility.FaciityContract
import com.snj07.rassignment.ui.splash.SplashContract
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashContract.SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashContract.SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainContract.MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainContract.MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FaciityContract.FacilityViewModel::class)
    abstract fun bindFilterViewModel(faciityViewModel: FaciityContract.FacilityViewModel): ViewModel


}
