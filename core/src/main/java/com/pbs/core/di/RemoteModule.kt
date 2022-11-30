package com.pbs.core.di

import com.pbs.core.data.source.datastore.DatastoreDataSource
import com.pbs.core.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideHttpClient(
        datastoreDataSource: DatastoreDataSource
    ) = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 3000
            socketTimeoutMillis = 10000
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            })
        }
    }

    @Provides
    fun provideRemoteSource(
        client: HttpClient
    ) = RemoteDataSource(client)
}