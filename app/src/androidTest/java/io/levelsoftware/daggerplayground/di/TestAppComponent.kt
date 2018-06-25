package io.levelsoftware.daggerplayground.di

import dagger.Component
import io.levelsoftware.daggerplayground.di.app.AndroidModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class, TestUtilsModule::class])
interface TestAppComponent {

}