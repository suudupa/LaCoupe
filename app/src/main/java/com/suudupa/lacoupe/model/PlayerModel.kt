package com.suudupa.lacoupe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PlayerModel (
        var name: String = "",
        var score: Int = 0
): Parcelable