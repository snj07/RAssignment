package com.snj07.rassignment.utils

import android.content.Context
import android.content.Intent
import com.snj07.rassignment.ui.main.MainActivity
import com.snj07.rassignment.ui.splash.SplashActivity

class Navigator {

    companion object {

        @JvmStatic
        fun navigateToMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        @JvmStatic
        fun navigateToSplashActivity(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }

    }

}