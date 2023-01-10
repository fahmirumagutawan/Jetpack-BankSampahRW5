package com.filkom.banksampahdelima.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.filkom.banksampahdelima.navigation.AppNavRoute
import com.filkom.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    val snackbarActive = mutableStateOf(false)
    val snackbarMessage = mutableStateOf("")
    val snackbarAction = mutableStateOf({})
    val snackbarActionLabel = mutableStateOf("Tutup")
    val showBottomMenu = mutableStateOf(false)
    val currentRoute = mutableStateOf(AppNavRoute.SplashScreen.name)

    //Dashboard screen
    val showDashboardTopBar = mutableStateOf(false)
}