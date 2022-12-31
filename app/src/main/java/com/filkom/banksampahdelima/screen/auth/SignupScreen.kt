package com.filkom.banksampahdelima.screen.auth

import android.os.Handler
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppBottomSheetScaffold
import com.filkom.banksampahdelima.component.AppButton
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.AppTextInputNormal
import com.filkom.banksampahdelima.component.AuthOtpSheet
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.viewmodel.SignupViewModel
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignupScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    val logoWidth = LocalConfiguration.current.screenWidthDp / 3
    val viewModel = hiltViewModel<SignupViewModel>()
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel.otpSecondLeft.value > 0) {
        while (viewModel.otpSecondLeft.value > 0) {
            delay(1000)
            viewModel.otpSecondLeft.value -= 1
        }
    }

    AppBottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            AuthOtpSheet(
                phoneNumber = viewModel.phoneNumberState.value,
                otpValue = viewModel.otpState.value,
                onOtpValueChanged = {
                    viewModel.otpState.value = it
                },
                onContinueClicked = {
                    val credential = PhoneAuthProvider.getCredential(
                        viewModel.verificationId.value,
                        viewModel.otpState.value
                    )

                    viewModel.signUpWithCredential(
                        credential = credential,
                        onSuccess = {
                            navigateToHome()
                        },
                        onFailed = {
                            showSnackbar(it.getMessage())
                        }
                    )
                },
                onResendClicked = {
                    viewModel.sendOtp(
                        onAutoCompleted = {
                            //save user info
                            it.smsCode?.let { code ->
                                viewModel.otpState.value = code
                                Handler().postDelayed(
                                    {
                                        navigateToHome()
                                    },
                                    2000
                                )
                            }
                        },
                        onCodeSent = { verificationId ->
                            viewModel.verificationId.value = verificationId
                        },
                        onFailed = { exception ->
                            showSnackbar(exception.getMessage())
                        }
                    )
                },
                otpSecondLeft = viewModel.otpSecondLeft.value
            )
        },
        sheetPeekHeight = 0.dp
    ) {
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
            AppTextInputNormal(
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Nama",
                value = viewModel.nameState.value,
                onValueChange = {
                    viewModel.nameFirstState.value = false
                    viewModel.nameState.value = it
                },
                isError = !viewModel.isNameValid.value,
                showWarningMessage = !viewModel.isNameValid.value,
                warningMessage = "Nama tidak boleh kosong!"
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
                warningMessage = "Format nomor telepon salah!",
                leadingIcon = {
                    AppText(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "+62",
                        textType = TextType.H3,
                        color = AppColor.Black
                    )
                }
            )

            // Alamat
            Spacer(modifier = Modifier.height(8.dp))
            AppTextInputNormal(
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Alamat",
                value = viewModel.addressState.value,
                onValueChange = {
                    viewModel.addressFirstState.value = false
                    viewModel.addressState.value = it
                },
                isError = !viewModel.isAddressValid.value,
                showWarningMessage = !viewModel.isAddressValid.value,
                warningMessage = "Alamat tidak boleh kosong!"
            )

//            // Password
//            Spacer(modifier = Modifier.height(8.dp))
//            AppTextInputNormal(
//                modifier = Modifier.fillMaxWidth(),
//                placeHolder = "Password",
//                value = viewModel.passwordState.value,
//                onValueChange = {
//                    viewModel.passwordFirstState.value = false
//                    viewModel.passwordState.value = it
//                },
//                isError = !viewModel.isPasswordValid.value,
//                showWarningMessage = !viewModel.isPasswordValid.value,
//                warningMessage = "Password harus lebih dari 6 karakter!"
//            )


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
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("\n")
                            append("Syarat dan Ketentuan")
                        }
                        append(" dan")
                        withStyle(
                            style = SpanStyle(
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
                    if (viewModel.isValidToSignUp.value) {
                        viewModel.sendOtp(
                            onAutoCompleted = {
                                //save user info
                                it.smsCode?.let { code ->
                                    viewModel.otpState.value = code
                                    Handler().postDelayed(
                                        {
                                            navigateToHome()
                                        },
                                        2000
                                    )
                                }
                            },
                            onCodeSent = { verificationId ->
                                viewModel.verificationId.value = verificationId
                            },
                            onFailed = { exception ->
                                showSnackbar(exception.getMessage())
                            }
                        )
                        coroutineScope.launch {
                            bottomSheetState.expand()
                        }
                        viewModel.otpSecondLeft.value = 20
                    } else {
                        viewModel.nameFirstState.value = false
                        viewModel.phoneNumberFirstState.value = false
                        viewModel.addressFirstState.value = false
                        viewModel.passwordFirstState.value = false
                        showSnackbar("Pastikan semua field telah terisi")
                    }
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
                        withStyle(
                            style = SpanStyle(
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
}