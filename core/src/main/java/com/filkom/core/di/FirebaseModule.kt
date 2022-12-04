package com.filkom.core.di

import com.filkom.core.data.source.firebase.FirebaseDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    fun provideFirebaseSource() = FirebaseDataSource()
}