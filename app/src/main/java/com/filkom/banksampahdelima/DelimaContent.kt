package com.filkom.banksampahdelima

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filkom.banksampahdelima.navigation.AppNavRoute
import com.filkom.banksampahdelima.screen.OnboardScreen
import com.filkom.banksampahdelima.screen.SplashScreen
import com.filkom.banksampahdelima.screen.auth.LoginScreen
import com.filkom.banksampahdelima.screen.auth.SignupScreen
import com.filkom.banksampahdelima.viewmodel.MainViewModel
import com.filkom.core.util.DelimaException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

lateinit var phoneNumberAuthOptions: (
    phoneNumber: String,
    auth: FirebaseAuth,
    callback: OnVerificationStateChangedCallbacks
) -> PhoneAuthOptions

@AndroidEntryPoint
class DelimaContent : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        phoneNumberAuthOptions = { phoneNumber, auth, callback ->
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(20L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callback)
                .build()
        }

        setContent {
            val mainViewModel by viewModels<MainViewModel>()
            val scaffoldState = rememberScaffoldState()
            if (mainViewModel.snackbarActive.value) {
                LaunchedEffect(key1 = true) {
                    val resetSnackbarState = {
                        mainViewModel.snackbarAction.value = {}
                        mainViewModel.snackbarActionLabel.value = "Tutup"
                        mainViewModel.snackbarMessage.value = ""
                        mainViewModel.snackbarActive.value = false
                    }
                    val snackbarData = scaffoldState
                        .snackbarHostState
                        .showSnackbar(
                            message = mainViewModel.snackbarMessage.value,
                            actionLabel = mainViewModel.snackbarActionLabel.value,
                            duration = SnackbarDuration.Short
                        )

                    when (snackbarData) {
                        SnackbarResult.Dismissed -> {
                            resetSnackbarState()
                        }

                        SnackbarResult.ActionPerformed -> {
                            when (mainViewModel.snackbarActionLabel.value) {
                                "Tutup" -> {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                                    resetSnackbarState()
                                }

                                else -> {
                                    mainViewModel.snackbarAction.value()
                                    resetSnackbarState()
                                }
                            }
                        }
                    }
                }
            }

            Scaffold(scaffoldState = scaffoldState) {
                DelimaNavHost(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun DelimaNavHost(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val showSnackbar: (message: String) -> Unit = { message ->
        mainViewModel.snackbarMessage.value = message
        mainViewModel.snackbarActive.value = true
    }
    val showSnackbarWithAction: (
        message: String,
        actionLabel: String,
        action: () -> Unit
    ) -> Unit = { message, actionLabel, action ->
        mainViewModel.snackbarActionLabel.value = actionLabel
        mainViewModel.snackbarAction.value = action
        mainViewModel.snackbarMessage.value = message
        mainViewModel.snackbarActive.value = true
    }

    NavHost(
        navController = navController,
        startDestination = AppNavRoute.SplashScreen.name
    ) {
        composable(route = AppNavRoute.SplashScreen.name) {
            SplashScreen(
                navigateToOnboard = {
                    navController.navigate(route = AppNavRoute.OnboardScreen.name)
                },
                navigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name)
                },
                navigateToHome = { /*TODO*/ }
            )
        }

        composable(route = AppNavRoute.OnboardScreen.name) {
            OnboardScreen(
                navigateToLogin = { navController.navigate(route = AppNavRoute.LoginScreen.name) },
                navigateToRegister = { navController.navigate(route = AppNavRoute.SignupScreen.name) })
        }

        composable(route = AppNavRoute.SignupScreen.name) {
            SignupScreen(
                navigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name)
                },
                navigateToHome = {
                    Log.e("SUCCESS", "NAVIGATED TO HOME")
                },
                showSnackbar = showSnackbar
            )
        }

        composable(route = AppNavRoute.LoginScreen.name) {
            LoginScreen(
                navigateToHome = {

                },
                navigateToForgetPassword = {

                },
                navigateToSignup = {
                    navController.navigate(route = AppNavRoute.SignupScreen.name)
                }
            )
        }
    }
}