package com.filkom.core.di

import com.filkom.core.data.source.firebase.FirebaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Provides
    fun provideFirebaseMessaging() = FirebaseMessaging.getInstance()

    @Provides
    fun provideFirebaseSource(
        firebaseAuth:FirebaseAuth,
        firebaseStorage: FirebaseStorage,
        firebaseMessaging: FirebaseMessaging
    ) = FirebaseDataSource(
        firebaseAuth = firebaseAuth,
        firebaseStorage = firebaseStorage,
        firebaseMessaging = firebaseMessaging
    )
}