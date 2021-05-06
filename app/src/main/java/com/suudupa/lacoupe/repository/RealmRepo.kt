package com.suudupa.lacoupe.repository

import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.model.UserModel
import io.realm.Realm
import io.realm.RealmObject

class RealmRepo {

    val realm = Realm.getDefaultInstance()

    fun insertOrUpdate(obj: RealmObject) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(obj)
        realm.commitTransaction()
    }

    private fun findUser(jerseyNumber: Int): UserModel? {
        return realm.where(UserModel::class.java)
                .equalTo("jerseyNumber", jerseyNumber)
                .findFirst()
    }

    fun updateUserScore(jerseyNumber: Int, isWin: Boolean) {
        realm.beginTransaction()
        findUser(jerseyNumber)?.inc(isWin)
        realm.commitTransaction()
    }

    fun matchId(): Int {
        val maxId = realm.where(MatchModel::class.java).max("matchId")
        return maxId?.toInt()?.plus(1) ?: 1
    }

    fun close() {
        realm.close()
    }
}