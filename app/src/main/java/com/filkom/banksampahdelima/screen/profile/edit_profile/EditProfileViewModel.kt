package com.filkom.banksampahdelima.screen.profile.edit_profile

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.core.data.repository.Repository
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    var nameState = mutableStateOf("Budi Setiawan")
    var nameFirstState = mutableStateOf(true)
    var isNameValid = derivedStateOf {
        nameState.value.isNotEmpty() || nameFirstState.value
    }

    var phoneNumberState = mutableStateOf("0821 6690 7128")
    var phoneNumberFirstState = mutableStateOf(true)
    val isPhoneNumberValid = derivedStateOf {
        (phoneNumberState.value.length >= 8 && phoneNumberState.value.isNotEmpty())
                || phoneNumberFirstState.value
    }

    var addressState = mutableStateOf("Jl. Bunga Mekar, No. 1, RT. 03")
    var addressFirstState = mutableStateOf(true)
    var isAddressValid = derivedStateOf {
        addressState.value.isNotEmpty() || addressFirstState.value
    }

    val isValidToSignUp = derivedStateOf {
        isNameValid.value && isPhoneNumberValid.value && isAddressValid.value
    }
}