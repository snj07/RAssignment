package com.snj07.rassignment.ui.main

import android.os.Bundle
import android.view.WindowManager
import com.snj07.rassignment.R
import com.snj07.rassignment.databinding.ActivityMainBinding
import com.snj07.rassignment.ui.base.BaseActivity
import com.snj07.rassignment.ui.main.facility.FacilityFragment
import com.snj07.rassignment.utils.Navigator
import com.snj07.rassignment.utils.extension.addFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainContract.MainViewModel>() {

    override fun getLayoutRes() = R.layout.activity_main
    override fun getModelClass() = MainContract.MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        initializeFragments()
    }


    private fun initializeFragments() {
        addFragment(R.id.container_fragment, FacilityFragment.newInstance(), "Filter")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Navigator.navigateToSplashActivity(this)
        finish()
    }

}