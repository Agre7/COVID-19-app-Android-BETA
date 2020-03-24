/*
 * Copyright © 2020 NHSX. All rights reserved.
 */

package com.example.colocate

import android.app.Application
import com.example.colocate.di.AppModule
import com.example.colocate.di.ApplicationComponent
import com.example.colocate.di.BluetoothModule
import com.example.colocate.di.DaggerApplicationComponent
import com.example.colocate.di.NetworkModule
import com.example.colocate.di.PersistenceModule
import timber.log.Timber

const val BASE_URL = "https://sonar-colocate-services.apps.cp.data.england.nhs.uk"

class ColocateApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .persistenceModule(PersistenceModule(this))
            .bluetoothModule(BluetoothModule(this))
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BASE_URL))
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}