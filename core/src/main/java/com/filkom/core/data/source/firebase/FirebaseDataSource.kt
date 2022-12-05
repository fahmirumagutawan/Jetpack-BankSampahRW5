package com.filkom.core.data.source.firebase

import com.filkom.core.util.DelimaException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseMessaging: FirebaseMessaging
) {
    /*Login with email and password*/
    fun loginWithEmailAndPassword(
        email:String,
        password:String,
        onSuccess:() -> Unit,
        onFailed:(DelimaException) -> Unit
    ){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailed(DelimaException(it.message)) }
    }

    /*Sign up with email and password*/
    fun signUpWithEmailAndPassword(
        email:String,
        password:String,
        onSuccess:() -> Unit,
        onFailed:(DelimaException) -> Unit
    ){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailed(DelimaException(it.message)) }
    }
}