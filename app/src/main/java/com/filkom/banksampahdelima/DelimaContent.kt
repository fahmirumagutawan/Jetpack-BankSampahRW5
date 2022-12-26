package com.filkom.banksampahdelima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DelimaContent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel by viewModels<MainViewModel>()

            DelimaNavHost(mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun DelimaNavHost(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppNavRoute.SplashScreen.name
    ) {
        composable(route = AppNavRoute.SplashScreen.name) {
            SplashScreen(
                navigateToOnboard = {
                    navController.navigate(route = AppNavRoute.OnboardScreen.name)
                },
                navigateToLogin = { /*TODO*/ },
                navigateToHome = { /*TODO*/ }
            )
        }

        composable(route = AppNavRoute.OnboardScreen.name) {
            OnboardScreen(
                navigateToLogin = {  navController.navigate(route = AppNavRoute.LoginScreen.name) },
                navigateToRegister = { navController.navigate(route = AppNavRoute.SignupScreen.name) })
        }

        composable(route = AppNavRoute.SignupScreen.name) {
            SignupScreen(
                navigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name)
                }
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