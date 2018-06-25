package io.levelsoftware.daggerplayground.models.events

import io.levelsoftware.daggerplayground.Schedulers
import io.levelsoftware.daggerplayground.di.production.ProductionHomeScope
import javax.inject.Inject

@ProductionHomeScope
class ProductionEventBusModel @Inject constructor(schedulers: Schedulers): EventBusModel(schedulers)