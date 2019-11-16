package br.com.mobile2you.m2ybase.ui.login

import br.com.mobile2you.m2ybase.data.remote.models.LoginRequest
import br.com.mobile2you.m2ybase.data.remote.models.LoginResponse
import br.com.mobile2you.m2ybase.ui.base.BasePresenter
import br.com.mobile2you.m2ybase.ui.base.BaseView

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