package com.snj07.rassignment.ui.main

import com.snj07.rassignment.ui.base.BaseViewModel
import javax.inject.Inject

interface MainContract {

    interface Inputs {
    }

    interface Outputs {
    }

    class MainViewModel @Inject constructor(
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

    }

}
