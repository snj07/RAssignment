package com.snj07.rassignment.di


import com.snj07.rassignment.di.annotation.FragmentScope
import com.snj07.rassignment.ui.main.facility.FacilityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
//
//    @FragmentScope
//    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
//    abstract fun contributeHomeFragment(): HomeFragment
//
//    @FragmentScope
//    @ContributesAndroidInjector(modules = [ArchiveFragmentModule::class])
//    abstract fun contributeArchiveFragment(): ArchiveFragment
//
//    @FragmentScope
//    @ContributesAndroidInjector(modules = [SettingFragmentModule::class])
//    abstract fun contributeSettingFragment(): SettingFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FacilityFragmentModule::class])
    abstract fun contributeFilterFragment(): FacilityFragment

}