package com.filkom.core.di

import android.content.Context
import com.filkom.core.data.source.firebase.FirebaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        @ApplicationContext context:Context,
        firebaseAuth:FirebaseAuth,
        firebaseStorage: FirebaseStorage,
        firebaseMessaging: FirebaseMessaging
    ) = FirebaseDataSource(
        context = context,
        firebaseAuth = firebaseAuth,
        firebaseStorage = firebaseStorage,
        firebaseMessaging = firebaseMessaging
    )
}