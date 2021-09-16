package com.myha.petadoption

import android.app.Application
import timber.log.Timber

/**
 * Created by Aria on 9/16/2021.
 */
class PetAdoptionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}