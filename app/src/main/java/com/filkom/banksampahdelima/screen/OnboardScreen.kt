package com.filkom.banksampahdelima.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppButton
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.AppTextButton
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.viewmodel.OnboardViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    //Attrs
    val viewModel = hiltViewModel<OnboardViewModel>()
    val imgWidth = LocalConfiguration.current.screenWidthDp / 1.5
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val halfHeight = LocalConfiguration.current.screenHeightDp / 2

    //Function

    //Content
    HorizontalPager(
        state = pagerState,
        count = OnboardItem.values().size
    ) { index ->
        OnboardItem.values()[index].let { item ->
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Img
                item {
                    Box(modifier = Modifier.height(halfHeight.dp), contentAlignment = Center) {
                        AsyncImage(
                            modifier = Modifier.width(imgWidth.dp),
                            model = item.imgId,
                            contentDescription = item.name
                        )
                    }
                }

                //Title & Content
                item {
                    Column(
                        modifier = Modifier.height(halfHeight.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Texts
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            AppText(text = item.title, textType = TextType.H1)

                            when (item) {
                                OnboardItem.Onboard3 -> {
                                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                        //daftar
                                        AppButton(
                                            modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                viewModel.saveFirstTimeOpenAppState(navigateToRegister)
                                            },
                                            text = "Daftar"
                                        )

                                        //masuk
                                        AppButton(
                                            modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                viewModel.saveFirstTimeOpenAppState(navigateToLogin)
                                            },
                                            text = "Sudah punya akun? Masuk",
                                            borderWidth = 1.dp,
                                            borderColor = AppColor.Black,
                                            textColor = AppColor.Black,
                                            backgroundColor = AppColor.White
                                        )
                                    }
                                }

                                else -> {
                                    AppText(text = item.text, textType = TextType.Body1)
                                }
                            }
                        }

                        //Btns
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 32.dp),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = SpaceBetween
                        ) {
                            HorizontalPagerIndicator(pagerState = pagerState)

                            Row(
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                //Kembali btn
                                if (index > 0) {
                                    AppTextButton(onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index - 1)
                                        }
                                    }) {
                                        AppText(text = "Kembali", textType = TextType.Body1)
                                    }
                                }

                                if (index < OnboardItem.values().size - 1) {
                                    AppButton(onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index + 1)
                                        }
                                    }, text = "Selanjutnya")
                                }
                            }
                        }
                    }
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