package com.vanjavier.yellow

import android.app.Application
import com.github.ajalt.timberkt.Timber
import dagger.hilt.android.HiltAndroidApp

/**
 * Prepare the application class.
 */

@HiltAndroidApp
class YellowApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber logging
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}