package com.filkom.banksampahdelima.screen

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    navigateToOnboard: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit
) {
    /**Attrs*/
    val viewModel = hiltViewModel<SplashViewModel>()
    val logoWidth = LocalConfiguration.current.screenWidthDp/3

    /**Function*/

    /**Content*/
    Box(
        modifier = Modifier.fillMaxSize().background(AppColor.GreenSuccess),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.width(logoWidth.dp),
                model = R.drawable.ic_splashscreen, 
                contentDescription = "Logo"
            )
            
            AppText(
                modifier = Modifier.width(logoWidth.dp),
                textAlign = TextAlign.Center,
                text = "Bank Sampah",
                textType = TextType.H2,
                color = AppColor.White
            )
        }
    }
}