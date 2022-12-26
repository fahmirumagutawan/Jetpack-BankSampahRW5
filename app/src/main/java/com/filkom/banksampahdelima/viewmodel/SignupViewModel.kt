package com.filkom.banksampahdelima.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    var usernameState = mutableStateOf("")
    var phoneNumberState = mutableStateOf("")
    var addressState = mutableStateOf("")
    var passwordState = mutableStateOf("")
    var isNameError = mutableStateOf(true)
    var isPhoneNumberError = mutableStateOf(true)
    var isAddressError = mutableStateOf(true)
    var isPasswordError = mutableStateOf(true)
    var checkedState = mutableStateOf(false)
    var isSignupSuccess = mutableStateOf(false)

    fun checkFormValidation() {
        if (usernameState.value.isNotEmpty())
            isNameError.value = false
        if (phoneNumberState.value.length in 10..13)
            isPhoneNumberError.value = false
        if (addressState.value.isNotEmpty())
            isAddressError.value = false
        if (passwordState.value.length > 8)
            isPasswordError.value = false
    }

    fun signUp() {
        if (!isNameError.value &&
            !isPhoneNumberError.value &&
            !isAddressError.value &&
            !isPasswordError.value &&
            checkedState.value
        ) {

            val email = phoneNumberState.value + "@gmail.com"
            val password = passwordState.value

            repository.signUpWithEmailAndPassword(
                email = email,
                password = password,
                onSuccess = {
                    Log.d("SIGNUP", email + password)
                },
                onFailed = {
                    Log.d("SIGNUP", it.getMessage())
                }
            )
        }
    }
}