package com.example.tests.domain.di.components

import com.example.tests.presentation.credentials.auth.AuthFragment
import com.example.tests.presentation.credentials.auth.LoadingFragment
import com.example.tests.presentation.credentials.registration.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface IAppComponent {

    fun inject(target: RegistrationFragment)
    fun inject(target: AuthFragment)
    fun inject(target: LoadingFragment)
}