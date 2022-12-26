package com.filkom.banksampahdelima.viewmodel


import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    var phoneNumberState = mutableStateOf("")
    val phoneNumberFirstState = mutableStateOf(true)
    val isPhoneNumberValid = derivedStateOf {
        phoneNumberState.value.length >= 8
                || phoneNumberFirstState.value
    }
    val isPhoneNumberNotEmpty = derivedStateOf {
        phoneNumberState.value.isNotEmpty()
                || phoneNumberFirstState.value
    }
    var passwordState = mutableStateOf("")
    val passwordFirstState = mutableStateOf(true)
    val isPasswordValid = derivedStateOf {
        passwordState.value.length >= 6
                || passwordFirstState.value
    }
    val isPasswordNotEmpty = derivedStateOf {
        passwordState.value.isNotEmpty()
                || passwordFirstState.value
    }
    var isLoginSuccess = mutableStateOf(false)

    fun login() {
        val email = phoneNumberState.value + "@gmail.com"
        val password = passwordState.value

        repository.loginWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess = {
                isLoginSuccess.value = true
            },
            onFailed = {
                isLoginSuccess.value = false
            }
        )
    }
}