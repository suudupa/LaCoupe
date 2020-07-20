package com.suudupa.lacoupe.model

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class PlayerModel (
        var name: String = "",
        var score: Int = 0
): RealmObject(), Parcelable