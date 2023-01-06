package com.filkom.core.data.source.firebase

import android.content.Context
import com.filkom.core.util.DelimaException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseDataSource @Inject constructor(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseMessaging: FirebaseMessaging
) {
    /*Login with email and password*/
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (DelimaException) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailed(DelimaException(it.message)) }
    }

    /*Sign up with email and password*/
    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (DelimaException) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailed(DelimaException(it.message)) }
    }

    //Send OTP
    fun sendOtp(
        phoneNumber: String,
        options: (
            phoneNumber: String,
            auth: FirebaseAuth,
            callback: OnVerificationStateChangedCallbacks
        ) -> PhoneAuthOptions,
        onAutoCompleted: (PhoneAuthCredential) -> Unit,
        onCodeSent: (String) -> Unit,
        onFailed: (DelimaException) -> Unit
    ) {
        PhoneAuthProvider
            .verifyPhoneNumber(
                options(
                    phoneNumber,
                    firebaseAuth,
                    object : OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                            onAutoCompleted(p0)
                        }

                        override fun onVerificationFailed(p0: FirebaseException) {
                            onFailed(DelimaException(p0.message ?: "Terjadi kesalahan"))
                        }

                        override fun onCodeSent(
                            p0: String,
                            p1: PhoneAuthProvider.ForceResendingToken
                        ) {
                            super.onCodeSent(p0, p1)
                            onCodeSent(p0)
                        }
                    }
                )
            )
    }

    //sign up with credential
    fun signUpWithCredential(
        credential: PhoneAuthCredential,
        name:String,
        address:String,
        onSuccess: () -> Unit,
        onFailed: (DelimaException) -> Unit
    ){
        firebaseAuth
            .signInWithCredential(credential)
            .addOnSuccessListener {
                //UPLOAD NAME & ADDRESS to DATABASE (either Firebase or our API)
            }
            .addOnFailureListener {
                onFailed(DelimaException(it.message ?: "Terjadi kesalahan"))
            }
    }

    //sign in with credential
    fun signInWithCredential(
        credential: PhoneAuthCredential,
        onSuccess: (AuthResult) -> Unit,
        onFailed: (DelimaException) -> Unit
    ){
        firebaseAuth
            .signInWithCredential(credential)
            .addOnSuccessListener {
                onSuccess(it)
            }
            .addOnFailureListener {
                onFailed(DelimaException(it.message ?: "Terjadi kesalahan"))
            }
    }
}