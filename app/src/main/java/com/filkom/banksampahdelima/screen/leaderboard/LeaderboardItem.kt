package com.filkom.banksampahdelima.screen.leaderboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor
import java.text.NumberFormat

@Composable
fun LeaderboardItem(
    name: String,
    rank: Int,
    point: Int
) {
    val componentSpacerWidth = LocalConfiguration.current.screenWidthDp / 8
    val formattedPoint = NumberFormat.getInstance().format(point).replace(",", ".")

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {

            // Rank
            AppText(
                text = rank.toString(),
                textType = TextType.H5
            )
            Spacer(modifier = Modifier.width(24.dp))

            // Profile Picture
            AsyncImage(
                model = R.drawable.ic_default_profile,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp*0.75).dp)
            ) {
                // User's Name
                Spacer(modifier = Modifier.width(16.dp))
                AppText(
                    text = name,
                    textType = TextType.H5
                )

                // Crown
                if (rank in 1..3) {
                    Spacer(modifier = Modifier.width(16.dp))
                    AsyncImage(
                        model = when (rank) {
                            1 -> R.drawable.green_crown
                            2 -> R.drawable.orange_crown
                            else -> R.drawable.silver_crown
                        },
                        contentDescription = "Crown",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(componentSpacerWidth.dp))
        }
        AppText(
            text = "$formattedPoint Poin",
            textType = TextType.H5,
            color = when (rank) {
                1 -> AppColor.Primary500
                2 -> AppColor.Orange
                3 -> AppColor.GrayPlaceholder
                else -> AppColor.Black
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 24.dp)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}