package io.levelsoftware.daggerplayground.di.demo

import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.levelsoftware.daggerplayground.models.Models
import io.levelsoftware.daggerplayground.models.events.DemoEventBusModel
import io.levelsoftware.daggerplayground.models.sample.DemoSampleModel

@Module
class DemoHomeModelModule {

    @DemoHomeScope
    @Provides
    fun provideModels(eventBusModel: DemoEventBusModel, sampleModel: DemoSampleModel): Models {
        return Models(eventBusModel, sampleModel)
    }

    @DemoHomeScope
    @Provides @ElementsIntoSet
    fun disposableItems(eventBusModel: DemoEventBusModel, sampleModel: DemoSampleModel) : Set<DisposableDependency> {
        return setOf(eventBusModel, sampleModel)
    }

}