package com.example.tests.domain.repositories.rest.api

import com.example.tests.base.IRestClient
import com.example.tests.domain.repositories.models.rest.UploadedFile
import com.example.tests.domain.repositories.rest.service.IDialogRestApiService
import com.soft.eac.thedepartmentgl.base.ABaseRestApi
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class DialogRestApi : ABaseRestApi<IDialogRestApiService> {


    @Inject
    constructor(client: IRestClient) : super(client)


    fun uploadAvatar(file: File): Observable<UploadedFile> {

        val part = MultipartBody.Part.createFormData("file",
            file.name + ".jpg",
            MultipartBody.create(MediaType.parse("image/*"), file)
        )

        return service.uploadAvatar(part)
    }
}