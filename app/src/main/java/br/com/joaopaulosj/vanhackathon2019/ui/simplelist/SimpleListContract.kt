package br.com.joaopaulosj.vanhackathon2019.ui.simplelist

import br.com.joaopaulosj.vanhackathon2019.ui.base.BasePresenter
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseView

interface SimpleListContract {

    interface View : BaseView<Presenter> {
        fun displayItems(items: List<String>)
        fun displayError(msg: String?)
        fun displayLoading(loading: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadItems()
    }

}