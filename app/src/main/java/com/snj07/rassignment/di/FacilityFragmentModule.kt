package com.snj07.rassignment.di

import com.snj07.rassignment.di.annotation.FragmentScope
import com.snj07.rassignment.ui.main.facility.FacilityRecyclerViewAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FacilityFragmentModule {

    @FragmentScope
    @Provides
    @Inject
    fun provideFacilityRecyclerViewAdapter(): FacilityRecyclerViewAdapter {
        return FacilityRecyclerViewAdapter()
    }

}