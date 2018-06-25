package io.levelsoftware.daggerplayground.di.production

import dagger.Subcomponent
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.levelsoftware.daggerplayground.models.Models

@Subcomponent(modules = [ProductionHomeModelModule::class])
@ProductionHomeScope
interface ProductionHomeComponent {

    fun getModels(): Models

    fun disposables(): Set<DisposableDependency>

}