package com.filkom.core.di

import com.filkom.core.data.repository.Repository
import com.filkom.core.data.source.datastore.DatastoreDataSource
import com.filkom.core.data.source.firebase.FirebaseDataSource
import com.filkom.core.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        RemoteModule::class,
        FirebaseModule::class,
        DatastoreModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideRepository(
        datastoreDataSource: DatastoreDataSource,
        firebaseDataSource: FirebaseDataSource,
        remoteDataSource: RemoteDataSource
    ) = Repository(datastoreDataSource, firebaseDataSource, remoteDataSource)
}