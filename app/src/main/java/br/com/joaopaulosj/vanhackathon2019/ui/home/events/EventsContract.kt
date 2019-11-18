package br.com.joaopaulosj.vanhackathon2019.ui.home.events

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.EventResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.BasePresenter
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseView

interface EventsContract {

    interface View : BaseView<Presenter> {
        fun displayItems(items: List<EventResponse>)
        fun displayError(msg: String?)
        fun displayLoading(loading: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadItems()
        fun filter(query: String): List<EventResponse>
    }

}