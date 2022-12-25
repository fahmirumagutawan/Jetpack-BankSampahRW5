package com.filkom.banksampahdelima.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.viewmodel.OnboardViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    //Attrs
    val viewModel = hiltViewModel<OnboardViewModel>()
    val imgWidth = LocalConfiguration.current.screenWidthDp / 2

    //Function

    //Content
    HorizontalPager(count = OnboardItem.values().size) { index ->
        OnboardItem.values()[index].let { item ->
            viewModel.onboardState.value = item

            LazyColumn(
                modifier = Modifier.padding(32.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //Img
                item {
                    AsyncImage(
                        modifier = Modifier.width(imgWidth.dp),
                        model = item.imgId,
                        contentDescription = item.name
                    )
                }
            }
        }
    }
}

enum class OnboardItem(
    val imgId: Int,
    val title: String,
    val text: String
) {
    Onboard1(
        R.drawable.onboard_1,
        "Tukarkan sampah dan kumpulkan poin",
        "Kumpulkan poin sebanyak-banyaknya"
    ),
    Onboard2(
        R.drawable.onboard_2,
        "Tukar poin dengan beragam sembako",
        "Kamu dapat menukar poin dengan minyak goreng, gular pasir, voucher, dll."
    ),
    Onboard3(
        R.drawable.onboard_3,
        "Mulai langkahmu sekarang",
        ""
    )
}