package com.filkom.banksampahdelima.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    lazyListState: LazyListState
) {
    val isTopbarVisible = remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.padding(horizontal = 16.dp)
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
                item{

                }
            }

            AnimatedVisibility(
                enter = slideInVertically { -it } + fadeIn(initialAlpha = 0f),
                exit = slideOutVertically { -it } + fadeOut(targetAlpha = 0f),
                visible = isTopbarVisible.value
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppColor.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
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
}

@Preview
@Composable
fun DashboardPrev() {
    DashboardScreen(lazyListState = rememberLazyListState())
}