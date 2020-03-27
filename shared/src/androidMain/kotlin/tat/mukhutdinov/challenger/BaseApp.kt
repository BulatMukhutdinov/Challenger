package tat.mukhutdinov.challenger

import android.app.Application
import tat.mukhutdinov.challenger.infrastructure.application
import timber.log.Timber

open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        application = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}