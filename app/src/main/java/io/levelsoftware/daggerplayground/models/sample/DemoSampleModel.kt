package io.levelsoftware.daggerplayground.models.sample

import android.content.SharedPreferences
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.levelsoftware.daggerplayground.di.demo.DemoHomeScope
import io.levelsoftware.daggerplayground.models.events.DemoEventBusModel
import timber.log.Timber
import javax.inject.Inject

@DemoHomeScope
class DemoSampleModel @Inject constructor(
        private val eventBusModel: DemoEventBusModel,
        private val sharedPreferences: SharedPreferences
): DisposableDependency(), SampleModel {

    init {
        Timber.d("Demo sample model initialized")
        disposables.add(eventBusModel.eventBus.subscribe {
            Timber.d("[${System.identityHashCode(this)}] Demo Event: $it")
        })
    }

    override fun writeValue(value: String) {
        sharedPreferences.edit()
                .putString("demoKey", "Demo saved value: $value " + System.identityHashCode(this))
                .apply()
    }

    override fun retrieveValue(): String {
        return sharedPreferences.getString("demoKey", "")
    }

}