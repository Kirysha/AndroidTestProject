package com.example.tests.base

import com.arellomobile.mvp.MvpView
//import moxy.MvpView
//import moxy.viewstate.strategy.alias.AddToEnd

interface IBaseView: MvpView {

    fun lock()

    fun unLock()

    fun onSuccess(message: String)

    fun onError(message: String)
}