package com.filkom.banksampahdelima.viewmodel


import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.core.data.repository.Repository
import com.filkom.core.util.DelimaException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    var phoneNumberState = mutableStateOf("")
    var phoneNumberFirstState = mutableStateOf(true)
    val isPhoneNumberValid = derivedStateOf {
        ( phoneNumberState.value.length >= 8 && phoneNumberState.value.isNotEmpty())
                || phoneNumberFirstState.value
    }
    var passwordState = mutableStateOf("")
    var passwordFirstState = mutableStateOf(true)
    val isPasswordValid = derivedStateOf {
        ( passwordState.value.length >= 6 && passwordState.value.isNotEmpty())
                || passwordFirstState.value
    }

    fun login(
        onSuccess:() -> Unit,
        onFailed:(DelimaException) -> Unit
    ) {
        val email = phoneNumberState.value + "@gmail.com"
        val password = passwordState.value

        repository.loginWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailed = onFailed
        )
    }
}