package com.example.tests.presentation.credentials.auth

import com.example.tests.base.IBaseView
import moxy.viewstate.strategy.alias.AddToEnd

interface IAuthView:IBaseView {
    @AddToEnd
    fun onSuccess()
}