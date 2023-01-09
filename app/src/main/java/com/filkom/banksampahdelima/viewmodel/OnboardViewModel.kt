package com.filkom.banksampahdelima.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filkom.banksampahdelima.screen.OnboardItem
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun saveFirstTimeOpenAppState(navigate:() -> Unit) = viewModelScope.launch {
        repository.saveFirstTimeState(false)
        navigate()
    }
}