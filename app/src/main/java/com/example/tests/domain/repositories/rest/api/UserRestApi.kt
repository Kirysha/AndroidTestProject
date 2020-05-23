package com.example.tests.domain.repositories.rest.api

import com.example.tests.base.IRestClient
import com.example.tests.domain.di.modules.NetModule
import com.example.tests.domain.repositories.models.rest.User
import com.example.tests.domain.repositories.rest.service.IUserRestApiService
import com.soft.eac.thedepartmentgl.base.ABaseRestApi
import javax.inject.Inject
import javax.inject.Named

class UserRestApi  : ABaseRestApi<IUserRestApiService> {


    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)


    fun registration(login: String, password: String)
            = service.registration(
        User(
            login = login,
            password = password
        )
    )


    fun login(login: String, password: String)
            = service.login(
        User(
            login = login,
            password = password
        )
    )


    fun refreshToken(refreshToken: String)
            = service.refreshToken(refreshToken)
}