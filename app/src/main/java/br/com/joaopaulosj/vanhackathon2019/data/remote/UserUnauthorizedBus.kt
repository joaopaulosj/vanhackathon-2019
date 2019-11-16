package br.com.joaopaulosj.vanhackathon2019.data.remote

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object UserUnauthorizedBus {

    val subject = PublishSubject.create<Any>()

    fun getEvents(): Observable<Any> {
        return subject
    }

    /**
     * Pass any event down to event listeners.
     */
    fun setEvent(error: Any) {
        subject.onNext(error)
    }
}