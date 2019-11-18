package br.com.joaopaulosj.vanhackathon2019.ui.home.notifications

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.NotificationResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.BasePresenter
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseView

interface NotificationsContract {

    interface View : BaseView<Presenter> {
        fun displayItems(items: List<NotificationResponse>)
        fun displayError(msg: String?)
        fun displayLoading(loading: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadItems()
    }

}