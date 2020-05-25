package com.example.tests.presentation.credentials.registration

import com.arellomobile.mvp.InjectViewState
import com.example.tests.base.SubRX
import com.example.tests.domain.repositories.UserRepository
import com.example.tests.presentation.main.MainActivity
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter : MvpPresenter<IRegistrationView> {

    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun registration(login: String, pass: String) {

        viewState.lock()
        userRepository.registration(SubRX { _, e ->
            viewState.unLock()

            if (e != null) {
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }

            MainActivity.show()

        }, login, pass)
    }
}