package br.com.mobile2you.m2ybase.ui.login

import android.content.Context
import android.os.Bundle
import br.com.mobile2you.m2ybase.BuildConfig
import br.com.mobile2you.m2ybase.R
import br.com.mobile2you.m2ybase.data.remote.models.LoginRequest
import br.com.mobile2you.m2ybase.data.remote.models.LoginResponse
import br.com.mobile2you.m2ybase.ui.base.BaseActivity
import br.com.mobile2you.m2ybase.utils.extensions.notImplementedFeature
import br.com.mobile2you.m2ybase.utils.extensions.showErrorToast
import br.com.mobile2you.m2ybase.utils.extensions.showToast
import br.com.mobile2you.m2ybase.utils.extensions.startActivitySlideTransition
import br.com.mobile2you.m2ybase.utils.validations.IsEmail
import br.com.mobile2you.m2ybase.utils.validations.IsLengthValid
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter by lazy {
        LoginPresenter().apply {
            attachView(this@LoginActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()

        if (BuildConfig.DEBUG) {
            loginEmailEt.text = "gustavofazani95@gmail.com"
            loginPassEt.text = "12345678"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun setListeners() {
        loginSigninBtn.setOnClickListener {
            validateFields().let { msg ->
                if (!msg.isEmpty()) showToast(msg)
                else {
                    val userdata = LoginRequest(loginEmailEt.text, loginPassEt.text)
                    presenter.onLoginClicked(userdata)
                }
            }
        }
        loginRegisterBtn.setOnClickListener { presenter.onRegisterClicked() }
        loginForgotPassBtn.setOnClickListener { presenter.onForgotPasswordClicked() }
    }

    private fun validateFields(): String {
        loginEmailEt.text.let {
            if (!IsEmail.isValid(it))
                return getString(R.string.login_error_email)
        }

        loginPassEt.text.let {
            if (!IsLengthValid.isValid(it, 6, false))
                return getString(R.string.login_error_password, 6)
        }
        return ""
    }

    override fun displayLoading(loading: Boolean) {
        loginSigninBtn.setLoading(loading)
    }

    override fun onLoginSucceeded(it: LoginResponse) {
        showToast("Login realizado com sucesso")
    }

    override fun displayError(message: String?) {
        showErrorToast(message ?: getString(R.string.unknown_error))
    }

    override fun openRegister() {
        notImplementedFeature()
    }

    override fun openForgotPassword() {
    }
}