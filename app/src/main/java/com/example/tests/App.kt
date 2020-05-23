package com.example.tests

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tests.domain.di.components.IAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {
        lateinit var appContext:Context
        lateinit var appComponent: IAppComponent
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
