package com.example.tests.domain.repositories.rest.service

import com.example.tests.domain.repositories.models.rest.UploadedFile
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IDialogRestApiService {
    /**
     * Регистрация нового профиля пользователя
     */
    @Multipart
    @POST("/upload/v1/avatar")
    fun uploadAvatar(@Part file: MultipartBody.Part): Observable<UploadedFile>

}