package com.filkom.banksampahdelima.screen.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppButton
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToForgetPassword: () -> Unit,
    navigateToSignup: () -> Unit
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
        OutlinedTextField(
            value = viewModel.phoneNumberState.value,
            onValueChange = {
                viewModel.phoneNumberState.value = it
            },
            label = {
                Text(text = "Nomor Telepon")
            },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Password
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.passwordState.value,
            onValueChange = {
                viewModel.passwordState.value = it
            },
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppText(
            text = "Lupa Password?",
            textType = TextType.H5,
            color = Color.Gray
        )

        // Button
        Spacer(modifier = Modifier.height(72.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.login()
                if (viewModel.isLoginSuccess.value) {
                    navigateToHome()
                }
            },
            text = "Masuk"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navigateToSignup()
                }
            )
        }
    }
}