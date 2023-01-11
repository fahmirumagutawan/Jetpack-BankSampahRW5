package com.filkom.banksampahdelima.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.AppTextButton
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    lazyListState: LazyListState,
    onTukarPointClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onCalculatorClicked:() -> Unit,
    onCategoryClicked:() -> Unit,
    onLocationClicked:() -> Unit,
    onSeeMoreArticle:() -> Unit
) {
    val isTopbarVisible = remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState, modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            //Profile
            item {
                Row(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(54.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account"
                        )

                        Column {
                            AppText(text = "Halo, Fahmi", textType = TextType.Body1)
                            AppText(text = "Selamat datang", textType = TextType.ExtraSmall)
                        }
                    }
                }
            }

            //Banner
            item {

            }

            //Poin & Riwayat
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp),
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = AppColor.Primary650
                ) {
                    //background
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.fillMaxWidth(0.3f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(topStartPercent = 100))
                                .background(AppColor.Primary500)
                        )
                    }

                    //foreground item
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        //poin
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(AppColor.Primary600),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AttachMoney,
                                    contentDescription = "Money",
                                    tint = AppColor.White
                                )
                            }

                            Column {
                                AppText(
                                    text = "Poin Saya",
                                    textType = TextType.Body2,
                                    color = AppColor.White
                                )
                                AppText(
                                    text = "2250", textType = TextType.H1, color = AppColor.White
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            //btn tukar point
                            Column(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(),
                                        onClick = onTukarPointClicked
                                    )
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(AppColor.White.copy(alpha = 0.5f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = rememberAsyncImagePainter(model = R.drawable.ic_dashboard_redeem_point),
                                        contentDescription = "",
                                        tint = AppColor.White
                                    )
                                }

                                AppText(
                                    text = "Tukar Poin",
                                    textType = TextType.Body2,
                                    color = AppColor.White
                                )
                            }

                            //btn riwayat
                            Column(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(),
                                        onClick = onHistoryClicked
                                    )
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(AppColor.White.copy(alpha = 0.5f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = rememberAsyncImagePainter(model = R.drawable.ic_dashboard_history),
                                        contentDescription = "",
                                        tint = AppColor.White
                                    )
                                }

                                AppText(
                                    text = "Riwayat", textType = TextType.Body2,
                                    color = AppColor.White
                                )
                            }
                        }

                    }
                }
            }

            //Kalkulator, kategori, lokasi
            item {
                val itemWidth = LocalConfiguration.current.screenWidthDp / 5

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(),
                                onClick = onCalculatorClicked
                            )
                            .padding(8.dp)
                            .widthIn(max = itemWidth.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(AppColor.Primary500),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.ic_dashboard_kalkulator),
                                contentDescription = "",
                                tint = AppColor.White
                            )
                        }

                        AppText(
                            text = "Kalkulator Sampah",
                            textType = TextType.Body2,
                            color = AppColor.Black,
                            textAlign = TextAlign.Center
                        )
                    }

                    Column(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(),
                                onClick = onCategoryClicked
                            )
                            .padding(8.dp)
                            .widthIn(max = itemWidth.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(AppColor.Primary500),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.ic_dashboard_kategori),
                                contentDescription = "",
                                tint = AppColor.White
                            )
                        }

                        AppText(
                            text = "Kategori Sampah",
                            textType = TextType.Body2,
                            color = AppColor.Black,
                            textAlign = TextAlign.Center
                        )
                    }

                    Column(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(),
                                onClick = onLocationClicked
                            )
                            .padding(8.dp)
                            .widthIn(max = itemWidth.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(AppColor.Primary500),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.ic_dashboard_location),
                                contentDescription = "",
                                tint = AppColor.White
                            )
                        }

                        AppText(
                            text = "Lokasi Penukaran",
                            textType = TextType.Body2,
                            color = AppColor.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            //Artikel terbaru title
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(text = "Artikel Terbaru", textType = TextType.H4)
                    AppTextButton(onClick = onSeeMoreArticle) {
                        AppText(
                            text = "Lihat Semua",
                            textType = TextType.H5,
                            color = AppColor.GrayPlaceholder
                        )
                    }
                }
            }
        }

        AnimatedVisibility(enter = slideInVertically { -it } + fadeIn(initialAlpha = 0f),
            exit = slideOutVertically { -it } + fadeOut(targetAlpha = 0f),
            visible = isTopbarVisible.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.White)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(54.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Account"
                    )

                    Column {
                        AppText(text = "Halo, Fahmi", textType = TextType.Body1)
                        AppText(text = "Selamat datang", textType = TextType.ExtraSmall)
                    }
                }
            }
        }
    }
}