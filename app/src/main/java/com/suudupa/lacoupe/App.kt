package com.suudupa.lacoupe

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder().apply {
            name("LaCoupe.realm")
            schemaVersion(1)
            deleteRealmIfMigrationNeeded()
        }.build()
        Realm.setDefaultConfiguration(configuration)
    }
}