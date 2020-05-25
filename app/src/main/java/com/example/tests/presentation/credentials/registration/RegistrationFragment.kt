package com.example.tests.presentation.credentials.registration

import android.os.Bundle
import android.view.View
import com.example.tests.App
import com.example.tests.R
import com.example.tests.base.ABaseFragment
import com.example.tests.base.IBaseView
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.android.synthetic.main.fragment_registration.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

class RegistrationFragment: ABaseFragment(), IRegistrationView {

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter // Реализация для Dagger
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId() = R.layout.fragment_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rBtn.setOnClickListener {
            presenter.registration("${rLogin.text}", "${rPass.text}")
        }
    }
}