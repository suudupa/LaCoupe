package com.suudupa.lacoupe.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
open class UserModel(
        var fullName: String = "",
        @PrimaryKey
        var jerseyNumber: Int = 1,
        var gamesPlayed: Int = 0,
        @Index
        var wins: Int = 0,
        @Index
        var isMain: Boolean = false
) : RealmObject(), Parcelable {

    fun inc(isWin: Boolean) {
        gamesPlayed++
        if (isWin) {
            wins++
        }
    }
}