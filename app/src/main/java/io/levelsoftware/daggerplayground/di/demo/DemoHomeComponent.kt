package io.levelsoftware.daggerplayground.di.demo

import dagger.Subcomponent
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.levelsoftware.daggerplayground.models.Models

@Subcomponent(modules = [DemoHomeModelModule::class, DemoHomeUtilsModule::class])
@DemoHomeScope
interface DemoHomeComponent {

    fun getModels(): Models

    fun disposables(): Set<DisposableDependency>

}