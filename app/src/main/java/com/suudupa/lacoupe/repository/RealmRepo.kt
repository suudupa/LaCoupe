package com.suudupa.lacoupe.repository

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

    fun close() {
        realm.close()
    }
}