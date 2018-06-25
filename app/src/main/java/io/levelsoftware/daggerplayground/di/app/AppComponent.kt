package io.levelsoftware.daggerplayground.di.app

import android.app.Application
import com.squareup.leakcanary.RefWatcher
import dagger.BindsInstance
import dagger.Component
import io.levelsoftware.daggerplayground.di.demo.DemoHomeComponent
import io.levelsoftware.daggerplayground.di.demo.DemoHomeModelModule
import io.levelsoftware.daggerplayground.di.demo.DemoHomeUtilsModule
import io.levelsoftware.daggerplayground.di.production.ProductionHomeComponent
import io.levelsoftware.daggerplayground.di.production.ProductionHomeModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class, UtilsModule::class])
interface AppComponent {

    fun plus(modelModule: ProductionHomeModelModule): ProductionHomeComponent

    fun plus(modelModule: DemoHomeModelModule, utilsModule: DemoHomeUtilsModule): DemoHomeComponent

    fun getRefWatcher(): RefWatcher

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun refWatcher(refWatcher: RefWatcher): Builder

        fun build(): AppComponent
    }

}
