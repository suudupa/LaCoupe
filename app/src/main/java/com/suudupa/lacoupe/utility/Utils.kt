package com.suudupa.lacoupe.utility

import com.suudupa.lacoupe.R
import java.util.*

object Utils {

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