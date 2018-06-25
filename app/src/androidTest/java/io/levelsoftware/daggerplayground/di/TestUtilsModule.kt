package io.levelsoftware.daggerplayground.di

import dagger.Module
import dagger.Provides
import io.levelsoftware.daggerplayground.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
class TestUtilsModule {

    @Provides
    @Singleton
    fun provideSchedulers(): Schedulers {
        return Schedulers(AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread())
    }

}