package br.com.joaopaulosj.vanhackathon2019.ui.home.notifications

import br.com.joaopaulosj.vanhackathon2019.data.repositories.NotificationsRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import io.reactivex.disposables.CompositeDisposable

class NotificationsPresenter : NotificationsContract.Presenter {

    private var view: NotificationsContract.View? = null
    private var disposable = CompositeDisposable()

    override fun attachView(mvpView: NotificationsContract.View?) {
        view = mvpView
    }

    override fun detachView() {
        view = null
        disposable.dispose()
    }

    override fun loadItems() {
        view?.displayLoading(true)
        disposable.add(NotificationsRepository.getNotifications().singleSubscribe(
            onSuccess = {
                view?.displayLoading(false)
                view?.displayItems(it)
            },
            onError = {
                view?.displayLoading(false)
                view?.displayError(it.message)
            }
        ))
    }
}