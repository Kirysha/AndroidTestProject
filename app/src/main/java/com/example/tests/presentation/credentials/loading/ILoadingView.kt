package com.example.tests.presentation.credentials.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

interface ILoadingView : MvpView {
    @AddToEnd
    fun showAuth()
}