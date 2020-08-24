package com.suudupa.lacoupe.model

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class PlayerModel (
        var user: UserModel? = null,
        var score: Int = 0
): RealmObject(), Parcelable