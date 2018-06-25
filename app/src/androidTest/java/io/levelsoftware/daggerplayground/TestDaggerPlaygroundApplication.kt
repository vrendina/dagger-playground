package io.levelsoftware.daggerplayground

import com.squareup.leakcanary.RefWatcher
import io.levelsoftware.daggerplayground.di.Injector
import io.levelsoftware.daggerplayground.di.app.DaggerAppComponent
import org.mockito.Mockito
import org.mockito.Mockito.mock

class TestDaggerPlaygroundApplication : DaggerPlaygroundApplication() {

    override fun initializeInjector() {
        val appComponent = TestDaggerAppComponent.builder()
                .application(this)
                .refWatcher(mock(RefWatcher::class.java))
                .build()
        Injector.initialize(appComponent)
    }
}