package com.example.tests.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

interface IBaseView:MvpView {
    @AddToEnd
    fun lock()
    @AddToEnd
    fun unLock()
    @AddToEnd
    fun onSuccess(message: String)
    @AddToEnd
    fun onError(message: String)
}