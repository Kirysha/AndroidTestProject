package com.example.tests.presentation.credentials.auth

import com.example.tests.base.SubRX
import com.example.tests.domain.repositories.UserRepository
import com.example.tests.presentation.main.MainActivity
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AuthPresenter: MvpPresenter<IAuthView> {
    private var userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun auth(login: String, password: String) {

        userRepository.login(SubRX { _, e ->

            if (e != null) {
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }

            MainActivity.show()
        }, login, password)
    }
}