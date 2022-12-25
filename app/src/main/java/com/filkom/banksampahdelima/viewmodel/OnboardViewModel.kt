package com.filkom.banksampahdelima.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.banksampahdelima.screen.OnboardItem
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val onboardState = mutableStateOf(OnboardItem.Onboard1)
}