package br.com.joaopaulosj.vanhackathon2019.ui.login

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginRequest
import br.com.joaopaulosj.vanhackathon2019.data.repositories.LoginRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null
    private var repository = LoginRepository
    private var disposable = CompositeDisposable()

    override fun onLoginClicked(userData: LoginRequest) {
        view?.displayLoading(true)
        tryToLogin(userData)
    }

    private fun tryToLogin(userData: LoginRequest) {
        repository.signIn(userData)
                .singleSubscribe(
                        onSuccess = {
                            view?.displayLoading(false)
                            view?.onLoginSucceeded(it)
                        },
                        onError = {
                            view?.displayLoading(false)
                            view?.displayError(it.message)
                        }
                )
    }

    override fun onRegisterClicked() {
        view?.openRegister()
    }

    override fun onForgotPasswordClicked() {
        view?.openForgotPassword()
    }

    override fun attachView(mvpView: LoginContract.View?) {
        this.view = mvpView
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }
}