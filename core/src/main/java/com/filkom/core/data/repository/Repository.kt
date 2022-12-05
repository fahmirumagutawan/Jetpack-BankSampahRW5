package com.filkom.core.data.repository

import com.filkom.core.data.source.datastore.DatastoreDataSource
import com.filkom.core.data.source.firebase.FirebaseDataSource
import com.filkom.core.data.source.remote.RemoteDataSource
import com.filkom.core.util.DelimaException
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastoreDataSource: DatastoreDataSource,
    private val firebaseDataSource: FirebaseDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    /*Login with email and password*/
    fun loginWithEmailAndPassword(
        email:String,
        password:String,
        onSuccess:() -> Unit,
        onFailed:(DelimaException) -> Unit
    ) = firebaseDataSource.loginWithEmailAndPassword(email, password, onSuccess, onFailed)

    /*Sign up with email and password*/
    fun signUpWithEmailAndPassword(
        email:String,
        password:String,
        onSuccess:() -> Unit,
        onFailed:(DelimaException) -> Unit
    ) = firebaseDataSource.signUpWithEmailAndPassword(email, password, onSuccess, onFailed)
}