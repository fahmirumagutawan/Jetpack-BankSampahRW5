package com.filkom.banksampahdelima.component

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.navigation.AppNavRoute
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun AppBottomBar(
    fabSizeDp: Double,
    currentRoute: String,
    onItemClicked: (route: String) -> Unit
) {
    val halfWidth = LocalConfiguration.current.screenWidthDp / 2
    val halfFab = fabSizeDp / 2

    BottomAppBar(backgroundColor = AppColor.White, elevation = 16.dp, cutoutShape = CircleShape) {
        Row(
            modifier = Modifier.width(halfWidth.dp)
        ) {
            Row(
                modifier = Modifier.width((halfWidth - halfFab).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..1) {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = AppColor.Black),
                            onClick = {
                                onItemClicked(BottombarItem.values()[i].route)
                            }
                        ),
                        imageVector = BottombarItem.values()[i].icon,
                        contentDescription = BottombarItem.values()[i].name,
                        tint = when (BottombarItem.values()[i].route) {
                            currentRoute -> AppColor.Primary500
                            else -> AppColor.GrayDisabled
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(halfFab.dp))
        }
        Row(
            modifier = Modifier.width(halfWidth.dp)
        ) {
            Spacer(modifier = Modifier.width(halfFab.dp))
            Row(
                modifier = Modifier.width((halfWidth - halfFab).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 2..3) {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = AppColor.Black),
                            onClick = {
                                onItemClicked(BottombarItem.values()[i].route)
                            }
                        ),
                        imageVector = BottombarItem.values()[i].icon,
                        contentDescription = BottombarItem.values()[i].name,
                        tint = when (BottombarItem.values()[i].route) {
                            currentRoute -> AppColor.Primary500
                            else -> AppColor.GrayDisabled
                        }
                    )
                }
            }
        }
    }
}

enum class BottombarItem(
    val route: String,
    val icon: ImageVector,
    val word: String
) {
    Dashboard(
        AppNavRoute.DashboardScreen.name,
        Icons.Default.Home,
        "Dashboard"
    ),
    Order(
        AppNavRoute.OrderScreen.name,
        Icons.Default.Wallet,
        "Order"
    ),
    Notification(
        AppNavRoute.NotificationScreen.name,
        Icons.Default.Notifications,
        "Notifikasi"
    ),
    Profile(
        AppNavRoute.ProfileScreen.name,
        Icons.Default.AccountCircle,
        "Profil"
    )
}