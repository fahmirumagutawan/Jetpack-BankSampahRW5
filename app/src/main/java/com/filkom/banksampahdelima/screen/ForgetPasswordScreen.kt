package com.filkom.banksampahdelima.screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.filkom.banksampahdelima.component.AppTopBar
import com.filkom.banksampahdelima.viewmodel.ForgetPasswordViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForgetPasswordScreen(
    onBackClicked: () -> Unit
) {
    val viewModel = hiltViewModel<ForgetPasswordViewModel>()

    Scaffold(
        topBar = {
            AppTopBar(onBackClicked = onBackClicked, midTitle = "Lupa Password")
        }
    ) {

    }
}