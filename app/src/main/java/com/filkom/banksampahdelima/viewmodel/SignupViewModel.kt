package com.filkom.banksampahdelima.viewmodel

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    var nameState = mutableStateOf("")
    var nameFirstState = mutableStateOf(true)
    var isNameValid = derivedStateOf {
        nameState.value.isNotEmpty() || nameFirstState.value
    }

    var phoneNumberState = mutableStateOf("")
    var phoneNumberFirstState = mutableStateOf(true)
    val isPhoneNumberValid = derivedStateOf {
        ( phoneNumberState.value.length >= 8 && phoneNumberState.value.isNotEmpty())
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
        ( passwordState.value.length >= 6 && passwordState.value.isNotEmpty())
                || passwordFirstState.value
    }

    var checkedState = mutableStateOf(false)
    var isSignupSuccess = mutableStateOf(false)


    fun signUp() {
        if (!isNameValid.value &&
            !isPhoneNumberValid.value &&
            !isAddressValid.value &&
            !isPasswordValid.value &&
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