package com.filkom.banksampahdelima.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun AuthOtpSheet(
    phoneNumber: String,
    otpSecondLeft: Int,
    otpValue: String,
    onOtpValueChanged: (value: String) -> Unit,
    onResendClicked: () -> Unit,
    onContinueClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
            .background(AppColor.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = "Kode OTP telah dikirimkan ke nomor $phoneNumber",
                textType = TextType.Body2
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppText(text = "Masukkan kode OTP", textType = TextType.H3)
                AppTextInputNormal(
                    placeHolder = "Kode OTP Kamu",
                    value = otpValue,
                    onValueChange = onOtpValueChanged,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                //Alert text here, probably will provide this later
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (otpSecondLeft > 0) {
                    AppText(
                        text = "Kirim Ulang",
                        textType = TextType.Body2,
                        color = AppColor.GrayDisabled
                    )
                    AppText(
                        text = otpSecondLeft.toString(),
                        textType = TextType.H5,
                        color = AppColor.Black
                    )
                } else {
                    AppTextButton(onClick = onResendClicked) {
                        AppText(
                            text = "Kirim Ulang",
                            textType = TextType.Body2,
                            color = AppColor.Primary500
                        )
                    }
                }

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onContinueClicked,
                    text = "Lanjutkan",
                    enabled = otpValue.length > 0
                )
            }
        }
    }
}