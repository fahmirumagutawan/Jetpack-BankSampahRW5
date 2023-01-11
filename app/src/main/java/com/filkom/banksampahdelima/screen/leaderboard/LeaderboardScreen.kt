package com.filkom.banksampahdelima.screen.leaderboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.component.AppTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LeaderboardScreen() {
    Scaffold(
        bottomBar = {
            BottomLeaderboard(name = "Agung", rank = 120, point = 1640)
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            // TopBar
            AppTopBar(onBackClicked = { /*TODO*/ }, midTitle = "Leaderboard")

            /*TODO : Change to leaderboard list and use lazy list*/
            Spacer(modifier = Modifier.height(16.dp))
            LeaderboardItem("Suhendra", rank = 1, point = 20240)
            LeaderboardItem("Maria", rank = 2, point = 18110)
            LeaderboardItem("Bambang", rank = 3, point = 15040)
            LeaderboardItem("Tina", rank = 4, point = 15010)
            LeaderboardItem("Agung", rank = 5, point = 12040)
            LeaderboardItem("Dina", rank = 6, point = 12040)
        }
    }
}