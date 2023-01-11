@file:Suppress("DEPRECATION")

package com.filkom.banksampahdelima.screen.profile

import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.ui.theme.AppType

@Composable
fun ProfileScreen() {
    /*TODO : Change parameter to user's model, implemented profile features onClick()*/

    val dummyPhoneNumber = "081233445566"
    val formattedDummyPhoneNumber = PhoneNumberUtils.formatNumber(dummyPhoneNumber)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        Box(modifier = Modifier.fillMaxWidth()) {
            AppText(
                text = "Profil",
                textType = TextType.H3,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        }

        // Profile
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                // Profile picture
                AsyncImage(
                    model = R.drawable.img_default_profile_picture,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

                // Name and Phone
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    AppText(text = "Budi Setiawan", textType = TextType.H2)
                    Spacer(modifier = Modifier.height(4.dp))
                    AppText(
                        text = formattedDummyPhoneNumber,
                        textType = TextType.H5,
                        color = Color.Gray
                    )
                }

                // Edit profile
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = "edit profil",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black,
                        fontFamily = AppType.h3().fontFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }

        // Poin
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                AppText(
                    text = "Poin Saya",
                    textType = TextType.H2,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                AppText(
                    text = "2250",
                    textType = TextType.H1,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
        }

        // Verification
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(shape = RoundedCornerShape(80.dp), color = AppColor.LightGreen)
        ) {
            Column(modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 24.dp)
            ) {
                AppText(text = "Akun kamu belum diverifikasi", textType = TextType.H5)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(shape = RoundedCornerShape(35.dp), color = AppColor.Primary500)
                        .clickable {

                        }
                ) {
                    AppText(
                        text = "Cara mengajukan verifikasi",
                        textType = TextType.H5,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }

        // Settings
        Spacer(modifier = Modifier.height(32.dp))
        ProfileFeaturesItem(title = "Tanya Kami")
        ProfileFeaturesItem(title = "Tentang Kami")
        ProfileFeaturesItem(title = "Ganti Password")
        ProfileFeaturesItem(title = "Keluar", titleColor = Color.Red)
    }
}