package io.levelsoftware.daggerplayground.models.events

import io.levelsoftware.daggerplayground.Schedulers
import io.levelsoftware.daggerplayground.di.DisposableDependency
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

open class EventBusModel constructor(schedulers: Schedulers): DisposableDependency() {

    val eventBus: PublishSubject<String> = PublishSubject.create()

    init {
        Timber.d("************** Initialize event bus model **************")
        disposables.add(Observable.interval(5, TimeUnit.SECONDS, schedulers.networkIO)
                .observeOn(schedulers.main)
                .subscribe {
                    Timber.d("[${System.identityHashCode(this)}] Event bus model event #$it")
                    eventBus.onNext("Event # $it")
                })
    }

}