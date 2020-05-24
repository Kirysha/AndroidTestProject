package com.example.tests

import android.app.Application
import android.content.Context
import com.example.tests.domain.di.components.AppComponent
import com.example.tests.domain.di.components.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {
        lateinit var appContext:Context
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        appComponent = DaggerAppComponent.create()
        initRealm()
    }

    private fun initRealm() {

        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        )
    }
}
