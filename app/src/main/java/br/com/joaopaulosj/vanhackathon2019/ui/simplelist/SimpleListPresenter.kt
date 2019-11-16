package br.com.joaopaulosj.vanhackathon2019.ui.simplelist

import io.reactivex.disposables.CompositeDisposable

class SimpleListPresenter : SimpleListContract.Presenter {

    private var mView: SimpleListContract.View? = null
    private var mDisposable = CompositeDisposable()

    override fun attachView(mvpView: SimpleListContract.View?) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
        mDisposable.dispose()
    }

    override fun loadItems() {
        //todo change the repository and the method that is called
        mView?.displayLoading(true)
//        mDisposable.add(JsonPlaceholderRepository.getPolls().singleSubscribe(
//                onSuccess = {
//                    mView?.displayLoading(false)
//                    mView?.displayItems(it)
//                },
//                onError = {
//                    mView?.displayLoading(false)
//                    mView?.displayError(it.message)
//                }
//        ))
    }
}