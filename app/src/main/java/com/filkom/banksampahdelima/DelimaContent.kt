package com.filkom.banksampahdelima

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filkom.banksampahdelima.component.AppBottomBar
import com.filkom.banksampahdelima.navigation.AppNavRoute
import com.filkom.banksampahdelima.screen.ForgetPasswordScreen
import com.filkom.banksampahdelima.screen.OnboardScreen
import com.filkom.banksampahdelima.screen.SplashScreen
import com.filkom.banksampahdelima.screen.auth.LoginScreen
import com.filkom.banksampahdelima.screen.auth.SignupScreen
import com.filkom.banksampahdelima.ui.theme.AppColor
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
            val navController = rememberNavController()
            val fabSize = remember { mutableStateOf(0.0) }
            val localDensity = LocalDensity.current

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
            navController.addOnDestinationChangedListener { _, destination, _ ->
                destination.route?.let {
                    mainViewModel.currentRoute.value = it
                }

                when (destination.route) {
                    AppNavRoute.DashboardScreen.name -> {
                        mainViewModel.showBottomMenu.value = true
                    }

                    AppNavRoute.OrderScreen.name -> {
                        mainViewModel.showBottomMenu.value = true
                    }

                    AppNavRoute.NotificationScreen.name -> {
                        mainViewModel.showBottomMenu.value = true
                    }

                    AppNavRoute.ProfileScreen.name -> {
                        mainViewModel.showBottomMenu.value = true
                    }

                    else -> {
                        mainViewModel.showBottomMenu.value = false
                    }
                }
            }

            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = {
                    if (mainViewModel.showBottomMenu.value) {
                        AppBottomBar(
                            fabSizeDp = fabSize.value,
                            currentRoute = mainViewModel.currentRoute.value,
                            onItemClicked = { route ->
                                navController.navigate(route = route)
                            }
                        )
                    }
                },
                floatingActionButton = {
                    if(mainViewModel.showBottomMenu.value){
                        FloatingActionButton(
                            backgroundColor = AppColor.Primary500,
                            modifier = Modifier.onSizeChanged {
                                localDensity.run {
                                    fabSize.value = it.width.toDp().value.toDouble()
                                }
                            },
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Recycling,
                                contentDescription = "Recycle",
                                tint = AppColor.White
                            )
                        }
                    }
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.Center
            ) {
                DelimaNavHost(navController = navController, mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun DelimaNavHost(navController: NavHostController, mainViewModel: MainViewModel) {
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
                    navController.navigate(route = AppNavRoute.OnboardScreen.name) {
                        popUpTo(route = AppNavRoute.SplashScreen.name) {
                            inclusive = true
                        }
                    }
                },
                navigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name) {
                        popUpTo(route = AppNavRoute.SplashScreen.name) {
                            inclusive = true
                        }
                    }
                },
                navigateToHome = {
                    navController.navigate(route = AppNavRoute.DashboardScreen.name) {
                        popUpTo(route = AppNavRoute.SplashScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = AppNavRoute.OnboardScreen.name) {
            OnboardScreen(
                navigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name) {
                        popUpTo(route = AppNavRoute.OnboardScreen.name) {
                            inclusive = true
                        }
                    }
                },
                navigateToRegister = {
                    navController.navigate(route = AppNavRoute.SignupScreen.name) {
                        popUpTo(route = AppNavRoute.OnboardScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = AppNavRoute.SignupScreen.name) {
            SignupScreen(
                navigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name)
                },
                navigateToHome = {

                },
                showSnackbar = showSnackbar
            )
        }

        composable(route = AppNavRoute.LoginScreen.name) {
            LoginScreen(
                navigateToHome = {

                },
                navigateToForgetPassword = {
                    navController.navigate(route = AppNavRoute.ForgetPasswordScreen.name)
                },
                navigateToSignup = {
                    navController.navigate(route = AppNavRoute.SignupScreen.name)
                },
                showSnackbar = showSnackbar
            )
        }

        composable(route = AppNavRoute.ForgetPasswordScreen.name) {
            ForgetPasswordScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = AppNavRoute.DashboardScreen.name) {

        }

        composable(route = AppNavRoute.OrderScreen.name) {

        }

        composable(route = AppNavRoute.NotificationScreen.name) {

        }

        composable(route = AppNavRoute.ProfileScreen.name) {

        }
    }
}