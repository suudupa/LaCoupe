package com.suudupa.lacoupe.model

import android.os.Parcelable
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
open class MatchModel(
        @PrimaryKey
        var matchId: String = "",
        var date: String = "",
        var field: String = "",
        var players: @RawValue RealmList<PlayerModel> = RealmList()
) : RealmObject(), Parcelable