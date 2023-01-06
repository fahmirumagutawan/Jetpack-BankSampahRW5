package com.filkom.core.data.source.remote

import com.filkom.core.model.data.request.RegisterUserRequest
import com.filkom.core.model.data.response.RegisterUserResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client:HttpClient
) {
    suspend fun saveUserInfoAfterRegister(
        request:RegisterUserRequest
    ) = client.post<RegisterUserResponse> {
        url(HttpRoutes.USER_ENDPOINT)
        contentType(ContentType.Application.Json)
        body = request
    }
}