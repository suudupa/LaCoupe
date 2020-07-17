package com.suudupa.lacoupe.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
open class UserModel(
        @PrimaryKey
        var fullName: String = "",
        var jerseyNumber: Int = 1
) : RealmObject(), Parcelable