package com.example.tests.presentation.credentials.auth

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.example.tests.domain.repositories.UserRepository
import com.example.tests.presentation.main.MainActivity
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LoadingPresenter : MvpPresenter<ILoadingView> {

    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadStaticResources()
    }

    fun loadStaticResources() {
        Handler().postDelayed({

            val user = userRepository.getUser()
            if (user != null) {
                MainActivity.show()
                return@postDelayed
            }

            viewState.showAuth()

        }, 500)
    }
}