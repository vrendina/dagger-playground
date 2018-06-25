package io.levelsoftware.daggerplayground.di.production

import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.levelsoftware.daggerplayground.models.Models
import io.levelsoftware.daggerplayground.models.events.ProductionEventBusModel
import io.levelsoftware.daggerplayground.models.sample.ProductionSampleModel

@Module
class ProductionHomeModelModule {

    @ProductionHomeScope
    @Provides
    fun provideModels(eventBusModel: ProductionEventBusModel, sampleModel: ProductionSampleModel): Models {
        return Models(eventBusModel, sampleModel)
    }

    @ProductionHomeScope
    @Provides @ElementsIntoSet
    fun disposableItems(eventBusModel: ProductionEventBusModel, sampleModel: ProductionSampleModel): Set<DisposableDependency> {
        return setOf(eventBusModel, sampleModel)
    }
}