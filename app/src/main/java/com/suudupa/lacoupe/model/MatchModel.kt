package com.suudupa.lacoupe.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
open class MatchModel(
        @PrimaryKey
        var matchId: String = "",
        var date: String = "",
        var players: List<PlayerModel> = ArrayList()
) : RealmObject(), Parcelable