package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

fun <T> Single<T>.singleSubscribe(
        onLoading: ((t: Boolean) -> Unit)? = null,
        onSuccess: ((t: T) -> Unit)? = null,
        onError: ((e: Throwable) -> Unit)? = null,
        subscribeOnScheduler: Scheduler? = Schedulers.io(),
        observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()): DisposableSingleObserver<T> {
    
    onLoading?.let { it(true) }
    
    return this.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribeWith(object : DisposableSingleObserver<T>() {
                override fun onSuccess(t: T) {
                    onLoading?.let { it(false) }
                    onSuccess?.let { it(t) }
                }
                
                override fun onError(e: Throwable) {
                    onLoading?.let { it(false) }
                    onError?.let { it(e) }
                }
            })
}

fun <T> Observable<T>.observableSubscribe(
        onLoading: ((t: Boolean) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onNext: ((t: T) -> Unit)? = null,
        onError: ((e: Throwable) -> Unit)? = null,
        subscribeOnScheduler: Scheduler? = Schedulers.io(),
        observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()): DisposableObserver<T> {
    
    onLoading?.let { it(true) }
    
    return this.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribeWith(object : DisposableObserver<T>() {
                override fun onNext(t: T) {
                    onLoading?.let { it(false) }
                    onNext?.let { it(t) }
                }
                
                override fun onComplete() {
                    onLoading?.let { it(false) }
                    onComplete?.let { it() }
                }
                
                override fun onError(e: Throwable) {
                    onLoading?.let { it(false) }
                    onError?.let { it(e) }
                }
            })
}