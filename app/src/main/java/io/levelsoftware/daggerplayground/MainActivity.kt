package io.levelsoftware.daggerplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.levelsoftware.daggerplayground.di.Injector
import io.levelsoftware.daggerplayground.models.sample.SampleModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var leakTest: SampleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // In production scope, get production models
        var models = Injector.getProductionHomeComponent().getModels()
        models.sampleModel.writeValue("Testing the production model")
        Timber.d(models.sampleModel.retrieveValue())

        // Hold a reference to the sample model (disposable model) after destroying the component to generate a leak
//        leakTest = models.sampleModel

        // In demo scope, now get demo models
        models = Injector.getDemoHomeComponent().getModels()
        models.sampleModel.writeValue("Testing the demo model")
        Timber.d(models.sampleModel.retrieveValue())

        // Back in production scope
        models = Injector.getProductionHomeComponent().getModels()
        models.sampleModel.writeValue("Testing the production model again")
        Timber.d(models.sampleModel.retrieveValue())

    }
}
