package br.com.joaopaulosj.vanhackathon2019.ui.home.events

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.EventResponse
import br.com.joaopaulosj.vanhackathon2019.data.repositories.EventsRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import io.reactivex.disposables.CompositeDisposable

class EventsPresenter : EventsContract.Presenter {

    private var mView: EventsContract.View? = null
    private var mDisposable = CompositeDisposable()
    private var events = listOf<EventResponse>()
    private var filtered = listOf<EventResponse>()


    override fun attachView(mvpView: EventsContract.View?) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
        mDisposable.dispose()
    }

    override fun loadItems() {
        mView?.displayLoading(true)
        mDisposable.add(EventsRepository.getEvents().singleSubscribe(
                onSuccess = {
                    events = it
                    filtered = it
                    mView?.displayLoading(false)
                    mView?.displayItems(it)
                },
                onError = {
                    mView?.displayLoading(false)
                    mView?.displayError(it.message)
                }
        ))
    }

    override fun filter(query: String): List<EventResponse> {
        filtered = events.filter {
            it.name.contains(query, true)
                    || it.city.contains(query, true)
                    || it.country.contains(query, true)
                    || it.theEvent.contains(query, true)
        }
        return filtered
    }
}