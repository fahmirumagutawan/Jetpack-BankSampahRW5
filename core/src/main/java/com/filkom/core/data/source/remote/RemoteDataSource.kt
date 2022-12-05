package com.filkom.core.data.source.remote

import io.ktor.client.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client:HttpClient
) {
}