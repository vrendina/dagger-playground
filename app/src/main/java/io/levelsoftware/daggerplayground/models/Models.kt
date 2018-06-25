package io.levelsoftware.daggerplayground.models

import io.levelsoftware.daggerplayground.models.events.EventBusModel
import io.levelsoftware.daggerplayground.models.sample.SampleModel

/**
 * Convenience class to wrap all models.
 */
class Models constructor(
        val eventBusModel: EventBusModel,
        val sampleModel: SampleModel
)
