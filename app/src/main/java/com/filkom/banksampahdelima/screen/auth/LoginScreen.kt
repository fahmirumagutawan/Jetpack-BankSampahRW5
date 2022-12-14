package com.filkom.banksampahdelima.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.*
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToForgetPassword: () -> Unit,
    navigateToSignup: () -> Unit,
    showSnackbar:(String) -> Unit
) {
    val logoWidth = LocalConfiguration.current.screenWidthDp / 3
    val viewModel = hiltViewModel<LoginViewModel>()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        AsyncImage(
            model = R.drawable.ic_auth,
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 64.dp)
                .width(logoWidth.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(72.dp))
        AppText(
            text = "Halo, Selamat Datang",
            textType = TextType.H2
        )
        AppText(
            text = "Masuk",
            textType = TextType.H4,
            color = Color.Gray
        )

        // Phone Number
        Spacer(modifier = Modifier.height(8.dp))
        AppTextInputNormal(
            modifier = Modifier.fillMaxWidth(),
            placeHolder = "Nomor Telepon",
            value = viewModel.phoneNumberState.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                viewModel.phoneNumberFirstState.value = false
                viewModel.phoneNumberState.value = it
            },
            isError = !viewModel.isPhoneNumberValid.value,
            showWarningMessage = !viewModel.isPhoneNumberValid.value,
            warningMessage = "Pastikan format nomor anda benar",
            leadingIcon = {
                AppText(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "+62",
                    textType = TextType.H3,
                    color = AppColor.Black
                )
            }
        )

        // Password
        Spacer(modifier = Modifier.height(8.dp))
        AppTextInputNormal(
            modifier = Modifier.fillMaxWidth(),
            placeHolder = "Password",
            value = viewModel.passwordState.value,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                viewModel.passwordFirstState.value = false
                viewModel.passwordState.value = it
            },
            isError = !viewModel.isPasswordValid.value,
            showWarningMessage = !viewModel.isPasswordValid.value,
            warningMessage = "Pastikan password sepanjang 6 huruf atau lebih"
        )

        Spacer(modifier = Modifier.height(16.dp))
        AppTextButton(onClick = navigateToForgetPassword) {
            AppText(
                text = "Lupa Password?",
                textType = TextType.H5,
                color = Color.Gray
            )
        }

        // Button
        Spacer(modifier = Modifier.height(72.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.login(
                    onSuccess = {
                        navigateToHome()
                    },
                    onFailed = {
                        showSnackbar(it.getMessage())
                    }
                )
            },
            text = "Masuk"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextButton(onClick = navigateToSignup) {
                Text(
                    text = buildAnnotatedString {
                        append("Belum punya akun? ")
                        withStyle(style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                        ) {
                            append("Daftar")
                        }
                    },
                    fontFamily = FontFamily(Font(R.font.opensauceone_regular)),
                    fontSize = 14.sp
                )
            }
        }
    }
}