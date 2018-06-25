package io.levelsoftware.daggerplayground.di

import com.squareup.leakcanary.RefWatcher
import io.levelsoftware.daggerplayground.di.app.AppComponent
import io.levelsoftware.daggerplayground.di.demo.DemoHomeComponent
import io.levelsoftware.daggerplayground.di.demo.DemoHomeModelModule
import io.levelsoftware.daggerplayground.di.demo.DemoHomeUtilsModule
import io.levelsoftware.daggerplayground.di.production.ProductionHomeComponent
import io.levelsoftware.daggerplayground.di.production.ProductionHomeModelModule

object Injector {

    private lateinit var appComponent: AppComponent
    private var demoHomeComponent: DemoHomeComponent? = null
    private var productionHomeComponent: ProductionHomeComponent? = null

    @JvmStatic
    fun getAppComponent() = appComponent

    @JvmStatic
    val refWatcher: RefWatcher
        get() = appComponent.getRefWatcher()

    fun initialize(appComponent: AppComponent) {
        if (!this::appComponent.isInitialized) this.appComponent = appComponent
    }

    @JvmStatic
    fun getDemoHomeComponent(): DemoHomeComponent {
        val component = demoHomeComponent ?: appComponent.plus(DemoHomeModelModule(), DemoHomeUtilsModule())
        demoHomeComponent = component
        disposeProductionHomeComponent()
        return component
    }

    @JvmStatic
    fun getProductionHomeComponent(): ProductionHomeComponent {
        val component = productionHomeComponent ?: appComponent.plus(ProductionHomeModelModule())
        productionHomeComponent = component
        disposeDemoHomeComponent()
        return component
    }

    @JvmStatic
    fun disposeDemoHomeComponent() {
        demoHomeComponent?.also { component ->
            refWatcher.watch(component)
            disposeDependencies(component.disposables())
        }
        demoHomeComponent = null
    }

    @JvmStatic
    fun disposeProductionHomeComponent() {
        // Destroy the production home component when the demo home component is retrieved
        productionHomeComponent?.also { component ->
            refWatcher.watch(component)
            disposeDependencies(component.disposables())
        }
        productionHomeComponent = null
    }

    private fun disposeDependencies(disposables: Set<DisposableDependency>) {
        disposables.forEach {
            it.dispose()
            refWatcher.watch(it)
        }
    }

}