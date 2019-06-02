package com.snj07.rassignment.utils.extension

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        val toast = Toast.makeText(this, message, duration)
        toast.show()
    }
}

fun AppCompatActivity.addFragment(@IdRes containerViewId: Int, fragment: androidx.fragment.app.Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
            .add(containerViewId, fragment, tag)
            .commitNow()
}

fun AppCompatActivity.showFragment(tag: String) {
    supportFragmentManager.findFragmentByTag(tag)?.let {
        supportFragmentManager.beginTransaction()
                .show(it)
                .commitNow()
    }
}

fun AppCompatActivity.hideFragment(tag: String) {
    supportFragmentManager.findFragmentByTag(tag)?.let {
        supportFragmentManager.beginTransaction()
                .hide(it)
                .commitNow()
    }
}


fun ViewGroup?.inflater(): LayoutInflater {
    return LayoutInflater.from(this?.context)
}

fun View.showKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, SHOW_IMPLICIT)
    }, delay)
}

fun View.hideSoftKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromInputMethod(windowToken, 0)
    }, delay)
}

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

