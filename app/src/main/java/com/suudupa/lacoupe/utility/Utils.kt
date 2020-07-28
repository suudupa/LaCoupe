package com.suudupa.lacoupe.utility

import android.app.Activity
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.suudupa.lacoupe.R
import java.util.*

object Utils {

    fun changeStatusBarColor(activity: Activity?, color: Int) {
        activity?.let {
            it.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            it.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            it.window.statusBarColor = ContextCompat.getColor(it, color)
            it.window.navigationBarColor = ContextCompat.getColor(it, color)
        }
    }

    fun greeting(): Int {
        val calender = Calendar.getInstance()
        return when (calender.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> R.string.goodMorning
            in 12..16 -> R.string.goodAfternoon
            in 17..23 -> R.string.goodEvening
            else -> R.string.hello
        }
    }
}