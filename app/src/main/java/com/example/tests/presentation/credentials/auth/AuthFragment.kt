package com.example.tests.presentation.credentials.auth

import android.os.Bundle
import android.view.View
import com.example.tests.App
import com.example.tests.R
import com.example.tests.base.ABaseFragment
import com.example.tests.presentation.credentials.ICredentialsRouter
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class AuthFragment : ABaseFragment(), IAuthView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId() = R.layout.fragment_auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAuthorization.setOnClickListener {

            val login = "${etLogin.text}" // "null"
            val password = "${etPass.text}"

            if (login.isEmpty() || password.isEmpty()) {
                toast(R.string.error_login_passwd_undefined)
                return@setOnClickListener
            }

            presenter.auth(login, password)
        }

        btnRegistration.setOnClickListener {
            activity?.let {
                if (it is ICredentialsRouter)
                    it.showRegistration()
            }
        }
    }

    override fun lock() {
        visibility(ProgresAuthorization)
    }

    override fun unLock() {
        visibility(ProgresAuthorization, false)
    }

    override fun onSuccess() {
        toast("SUCCESS")
        // Отправить на главную форму
    }
}