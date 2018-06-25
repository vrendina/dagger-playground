package io.levelsoftware.daggerplayground

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TestAndroidJUnitRunner: AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestDaggerPlaygroundApplication::javaClass.name, context)
    }
}