package com.filkom.banksampahdelima.viewmodel

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.banksampahdelima.phoneNumberAuthOptions
import com.filkom.core.data.repository.Repository
import com.filkom.core.util.DelimaException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var nameState = mutableStateOf("")
    var nameFirstState = mutableStateOf(true)
    var isNameValid = derivedStateOf {
        nameState.value.isNotEmpty() || nameFirstState.value
    }

    var phoneNumberState = mutableStateOf("")
    var phoneNumberFirstState = mutableStateOf(true)
    val isPhoneNumberValid = derivedStateOf {
        (phoneNumberState.value.length >= 8 && phoneNumberState.value.isNotEmpty())
                || phoneNumberFirstState.value
    }

    var addressState = mutableStateOf("")
    var addressFirstState = mutableStateOf(true)
    var isAddressValid = derivedStateOf {
        addressState.value.isNotEmpty() || addressFirstState.value
    }

    var passwordState = mutableStateOf("")
    var passwordFirstState = mutableStateOf(true)
    val isPasswordValid = derivedStateOf {
        (passwordState.value.length >= 6 && passwordState.value.isNotEmpty())
                || passwordFirstState.value
    }

    var checkedState = mutableStateOf(false)

    val isValidToSignUp = derivedStateOf {
        isNameValid.value &&
                isPhoneNumberValid.value &&
                isAddressValid.value &&
                isPasswordValid.value &&
                checkedState.value
    }

    fun signUp(
        onSuccess:(AuthResult) -> Unit,
        onFailed:(DelimaException) -> Unit
    ){
        val email = phoneNumberState.value + "@gmail.com"
        val password = passwordState.value

        repository.signUpWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess,
            onFailed
        )
    }
}