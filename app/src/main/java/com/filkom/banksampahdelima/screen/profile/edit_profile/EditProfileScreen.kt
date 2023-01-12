package com.filkom.banksampahdelima.screen.profile.edit_profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppButton
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.AppTextInputNormal
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.ui.theme.AppType

@Composable
fun EditProfileScreen() {
    /* TODO : Change TextFieldValue to user's info and implemented save button*/

    val viewModel = hiltViewModel<EditProfileViewModel>()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.TopStart)
                    .clickable {

                    }
            )

            AppText(
                text = stringResource(R.string.profil),
                textType = TextType.H3,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        }

        // Profile picture
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            // Profile picture
            AsyncImage(
                model = R.drawable.img_default_profile_picture,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }

        // Change profile picture
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Ganti foto profil",
                textDecoration = TextDecoration.Underline,
                color = Color.Gray,
                fontFamily = AppType.h3().fontFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

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


        // Button
        Spacer(modifier = Modifier.height(48.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (viewModel.isValidToSignUp.value) {
                    /* TODO : Implemented Save Button */
                } else {
                    viewModel.nameFirstState.value = false
                    viewModel.phoneNumberFirstState.value = false
                    viewModel.addressFirstState.value = false
                }
            },
            text = "Simpan"
        )
    }
}