package com.pbs.core.di

import android.content.Context
import com.pbs.core.data.source.datastore.DatastoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {
    @Provides
    fun provideDatastoreSource(
        @ApplicationContext context: Context
    ) = DatastoreDataSource(context)
}