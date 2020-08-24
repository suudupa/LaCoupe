package com.suudupa.lacoupe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class UserListModel (
        var users: ArrayList<UserModel> = ArrayList()
): Parcelable