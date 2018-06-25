package io.levelsoftware.daggerplayground

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Schedulers(val diskIO: Scheduler,
                 val networkIO: Scheduler,
                 val computation: Scheduler,
                 val main: Scheduler) {

    @Inject
    constructor() : this(
            diskIO = Schedulers.from(Executors.newSingleThreadExecutor()),
            networkIO = Schedulers.io(),
            computation = Schedulers.computation(),
            main = AndroidSchedulers.mainThread()
    )

}