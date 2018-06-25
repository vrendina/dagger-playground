package io.levelsoftware.daggerplayground.models.events

import io.levelsoftware.daggerplayground.Schedulers
import io.levelsoftware.daggerplayground.di.demo.DemoHomeScope
import javax.inject.Inject

@DemoHomeScope
class DemoEventBusModel @Inject constructor(schedulers: Schedulers): EventBusModel(schedulers)