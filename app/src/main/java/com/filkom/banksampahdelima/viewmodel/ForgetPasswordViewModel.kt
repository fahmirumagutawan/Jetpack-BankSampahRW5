package com.filkom.banksampahdelima.viewmodel

import androidx.lifecycle.ViewModel
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
}