package com.filkom.banksampahdelima.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository:Repository
):ViewModel() {
    fun getFirstTimeState(onRetrieved: (Boolean) -> Unit) = viewModelScope.launch {
        repository.getFirstTimeState().collectLatest{
            onRetrieved(it)
        }
    }

    fun getBearerToken(onRetrieved:(String) -> Unit) = viewModelScope.launch {
        repository.getBearerToken().collectLatest{
            onRetrieved(it)
        }
    }
}