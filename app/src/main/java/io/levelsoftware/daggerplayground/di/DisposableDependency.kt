package io.levelsoftware.daggerplayground.di

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base class for sub-component dependencies that are disposable when the sub-component is destroyed.
 */
abstract class DisposableDependency: Disposable {

    protected val disposables = CompositeDisposable()

    override fun isDisposed(): Boolean = disposables.isDisposed

    @CallSuper
    override fun dispose() {
        disposables.dispose()
    }

}