package com.suudupa.lacoupe.utility

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.suudupa.lacoupe.R
import java.text.SimpleDateFormat
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

    fun showErrorMessage(tv: TextView, msg: Int) {
        tv.setText(msg)
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        val fadeOut = AlphaAnimation(1.0f, 0.0f)
        tv.startAnimation(fadeIn)
        tv.startAnimation(fadeOut)
        fadeIn.duration = 1000
        fadeIn.fillAfter = true
        fadeOut.duration = 1000
        fadeOut.fillAfter = true
        fadeOut.startOffset = 1500 + fadeIn.startOffset
    }

    fun showToast(context: Context, msg: String) {
        val tv = TextView(context)
        tv.text = msg
        tv.setTextColor(Color.BLACK)
        val toast = Toast(context)
        toast.view = tv
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.TOP, 0, 380)
        toast.show()
    }

    fun todayDate(): String {
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}