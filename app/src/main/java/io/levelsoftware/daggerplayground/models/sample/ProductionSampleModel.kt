package io.levelsoftware.daggerplayground.models.sample

import android.content.SharedPreferences
import android.content.res.Resources
import io.levelsoftware.daggerplayground.R
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.levelsoftware.daggerplayground.di.production.ProductionHomeScope
import io.levelsoftware.daggerplayground.models.events.ProductionEventBusModel
import timber.log.Timber
import javax.inject.Inject

@ProductionHomeScope
class ProductionSampleModel @Inject constructor(
        private val eventBusModel: ProductionEventBusModel,
        private val sharedPreferences: SharedPreferences,
        private val resources: Resources
) : DisposableDependency(), SampleModel {

    companion object {
        const val PRODUCTION_KEY = "production_key"
    }

    init {
        Timber.d("Production sample model initialized")
        disposables.add(eventBusModel.eventBus.subscribe {
            Timber.d("[${System.identityHashCode(this)}] Production Event: $it")
        })
    }

    override fun writeValue(value: String) {
        sharedPreferences.edit()
                .putString(PRODUCTION_KEY, resources.getString(R.string.production_value) + value)
                .apply()
    }

    override fun retrieveValue(): String {
        return sharedPreferences.getString(PRODUCTION_KEY, "")
    }

}