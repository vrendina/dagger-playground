package io.levelsoftware.daggerplayground

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.levelsoftware.daggerplayground.di.Injector
import io.levelsoftware.daggerplayground.di.app.DaggerAppComponent
import timber.log.Timber

open class DaggerPlaygroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        initializeInjector()
        configureLogging()
    }

    open fun initializeInjector() {
        Injector.initialize(DaggerAppComponent.builder()
                .application(this)
                .refWatcher(LeakCanary.refWatcher(this).build())
                .build())
    }

    private fun configureLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}