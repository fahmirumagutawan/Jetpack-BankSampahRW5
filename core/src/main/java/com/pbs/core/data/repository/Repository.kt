package com.pbs.core.data.repository

import com.pbs.core.data.source.datastore.DatastoreDataSource
import com.pbs.core.data.source.firebase.FirebaseDataSource
import com.pbs.core.data.source.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastoreDataSource: DatastoreDataSource,
    private val firebaseDataSource: FirebaseDataSource,
    private val remoteDataSource: RemoteDataSource
) {

}