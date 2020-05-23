package com.example.tests.base

interface IRestClient {

    fun <S> createService(serviceClass: Class<S>) : S

    fun cancelAllRequests()
}