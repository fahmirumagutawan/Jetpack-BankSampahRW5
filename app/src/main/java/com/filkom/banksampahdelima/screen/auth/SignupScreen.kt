package com.filkom.banksampahdelima.screen.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppButton
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.viewmodel.SignupViewModel

@Composable
fun SignupScreen(
    navigateToLogin: () -> Unit,
) {
    val logoWidth = LocalConfiguration.current.screenWidthDp / 3
    val viewModel = hiltViewModel<SignupViewModel>()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = R.drawable.ic_auth,
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 64.dp)
                .width(logoWidth.dp)
                .align(CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(72.dp))
        AppText(
            text = "Mulai Langkah Hematmu!",
            textType = TextType.H2
        )
        AppText(
            text = "Buat Akun Baru",
            textType = TextType.H4,
            color = Color.Gray
        )

        // Name
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = viewModel.usernameState.value,
            onValueChange = {
                viewModel.usernameState.value = it
                viewModel.checkFormValidation()
            },
            label = {
                Text(text = "Nama")
            },
            singleLine = true,
            maxLines = 1,
            isError = viewModel.isNameError.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.isNameError.value) {
            Spacer(modifier = Modifier.height(4.dp))
            AppText(
                text = "Nama Tidak Boleh Kosong!",
                color = Color.Gray,
                textType = TextType.ExtraSmall
            )
        }


        // Phone Number
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.phoneNumberState.value,
            onValueChange = {
                viewModel.phoneNumberState.value = it
                viewModel.checkFormValidation()
            },
            label = {
                Text(text = "Nomor Telepon")
            },
            singleLine = true,
            maxLines = 1,
            isError = viewModel.isPhoneNumberError.value,
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
        if (viewModel.isPhoneNumberError.value) {
            Spacer(modifier = Modifier.height(4.dp))
            AppText(
                text = "Nomor Telepon Tidak Valid!",
                color = Color.Gray,
                textType = TextType.ExtraSmall
            )
        }

        // Alamat
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.addressState.value,
            onValueChange = {
                viewModel.addressState.value = it
                viewModel.checkFormValidation()
            },
            label = {
                Text(text = "Alamat")
            },
            singleLine = true,
            maxLines = 1,
            isError = viewModel.isAddressError.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.isAddressError.value) {
            Spacer(modifier = Modifier.height(4.dp))
            AppText(
                text = "Alamat Tidak Boleh Kosong!",
                color = Color.Gray,
                textType = TextType.ExtraSmall
            )
        }

        // Password
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.passwordState.value,
            onValueChange = {
                viewModel.passwordState.value = it
                viewModel.checkFormValidation()
            },
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            isError = viewModel.isPasswordError.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.isPasswordError.value) {
            Spacer(modifier = Modifier.height(4.dp))
            AppText(
                text = "Password Harus Lebih Dari 8 Karakter!",
                color = Color.Gray,
                textType = TextType.ExtraSmall
            )
        }


        // Terms
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = viewModel.checkedState.value,
                onCheckedChange = { viewModel.checkedState.value = it },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    checkedColor = Color.Green
                )
            )
            Text(
                text = buildAnnotatedString {
                    append("Dengan mendaftar, saya setuju dengan")
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                    ) {
                        append("\n")
                        append("Syarat dan Ketentuan")
                    }
                    append(" dan")
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                    ) {
                        append(" Kebijakan Privasi")
                    }
                },
                fontFamily = FontFamily(Font(R.font.opensauceone_regular)),
                fontSize = 14.sp
            )
        }

        // Button
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.signUp()
                if (viewModel.isSignupSuccess.value)
                    navigateToLogin()
            },
            text = "Daftar"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Sudah punya akun? ")
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                    ) {
                        append("Masuk")
                    }
                },
                fontFamily = FontFamily(Font(R.font.opensauceone_regular)),
                fontSize = 14.sp,
                modifier = Modifier.clickable { navigateToLogin() }
            )
        }
    }
}