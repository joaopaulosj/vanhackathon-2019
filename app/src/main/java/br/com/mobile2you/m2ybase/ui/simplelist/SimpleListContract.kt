package br.com.mobile2you.m2ybase.ui.simplelist

import br.com.mobile2you.m2ybase.ui.base.BasePresenter
import br.com.mobile2you.m2ybase.ui.base.BaseView

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