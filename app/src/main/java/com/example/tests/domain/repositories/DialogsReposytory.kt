package com.example.tests.domain.repositories

import com.example.tests.domain.repositories.models.DialogItem
import com.example.tests.domain.repositories.models.rest.UploadedFile
import com.example.tests.domain.repositories.rest.api.DialogRestApi
import java.io.File
import java.util.*
import javax.inject.Inject

class DialogsReposytory {
    private val rest: DialogRestApi

    @Inject
    constructor(rest: DialogRestApi) {
        this.rest = rest
    }

    fun loadDialogs(call: (List<DialogItem>) -> Unit) {

        val result = mutableListOf<DialogItem>()
        val random = Random(System.currentTimeMillis())
        val count = random.nextInt(900) + 100
        for (index in 0 until count)
            result.add(DialogItem("Title: $index", random.nextBoolean()))

        call(result)
    }
}