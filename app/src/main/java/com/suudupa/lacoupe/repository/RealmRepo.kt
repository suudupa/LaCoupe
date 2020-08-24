package com.suudupa.lacoupe.repository

import com.suudupa.lacoupe.model.MatchModel
import io.realm.Realm
import io.realm.RealmObject

class RealmRepo {

    val realm = Realm.getDefaultInstance()

    fun insertOrUpdate(className: RealmObject) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(className)
        realm.commitTransaction()
    }

    fun insertOrUpdateList(list: List<RealmObject>) {
        realm.executeTransaction { realm ->
            list.forEach { realm.insertOrUpdate(it) }
        }
    }

    fun matchId(): Int {
        val maxId = realm.where(MatchModel::class.java).max("matchId")
        return maxId?.toInt()?.plus(1) ?: 1
    }

    fun close() {
        realm.close()
    }
}