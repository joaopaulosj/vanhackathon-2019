package br.com.joaopaulosj.vanhackathon2019.ui.login

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginRequest
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.BasePresenter
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun displayLoading(loading: Boolean)
        fun openRegister()
        fun openForgotPassword()
        fun onLoginSucceeded(it: LoginResponse)
        fun displayError(message: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClicked(userData: LoginRequest)
        fun onRegisterClicked()
        fun onForgotPasswordClicked()
    }
}