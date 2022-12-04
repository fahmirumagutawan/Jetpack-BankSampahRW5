package com.filkom.core.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseMessaging: FirebaseMessaging
) {
}