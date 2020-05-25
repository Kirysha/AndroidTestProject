package com.example.tests.presentation.credentials.auth

import com.example.tests.App
import com.example.tests.R
import com.example.tests.base.ABaseFragment
import com.example.tests.presentation.credentials.ICredentialsRouter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

class LoadingFragment : ABaseFragment(), ILoadingView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId() = R.layout.fragment_auth

    override fun showAuth() {
        activity?.let {
            if (it is ICredentialsRouter)
                it.showAuth()
        }
    }
}