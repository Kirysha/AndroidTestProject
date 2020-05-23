package com.example.tests.presentation.credentials

import android.content.Intent
import android.os.Bundle
import com.example.tests.App
import com.example.tests.R
import com.example.tests.base.ABaseActivity
import com.example.tests.domain.repositories.local.UserStorage
import com.example.tests.presentation.credentials.auth.AuthFragment
import com.example.tests.presentation.credentials.auth.LoadingFragment
import com.example.tests.presentation.credentials.registration.RegistrationFragment

class CredantialsActivity : ABaseActivity(), ICredentialsRouter {

    companion object {

        private const val ARG_DROP_CREDENTIALS = "ARG_DROP_CREDENTIALS"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, CredantialsActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ARG_DROP_CREDENTIALS, true)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (savedInstanceState != null)
            return

        // Немного не верно с т.з. архитектуры, но тут больше и не нужно
        if (intent.getBooleanExtra(ARG_DROP_CREDENTIALS, false)) {
            UserStorage().dropCredentials()
            showAuth()
            return
        }

        showLoading()
    }

    override fun showLoading() {
        replace(LoadingFragment())
    }

    override fun showRegistration() {
        replace(RegistrationFragment(), "Registration")
    }

    override fun showAuth() {
        replace(AuthFragment())
    }
}