package com.snj07.rassignment.ui.main.facility

import com.snj07.rassignment.ui.base.BaseViewModel
import javax.inject.Inject

interface OptionContract {
    interface Inputs {

    }

    interface Outputs {

    }

    class OptionViewModel @Inject constructor(
    ) : BaseViewModel(), OptionContract.Inputs, OptionContract.Outputs {


        val inputs: OptionContract.Inputs = this
        val outputs: OptionContract.Outputs = this


    }
}