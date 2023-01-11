package com.filkom.banksampahdelima.screen.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.filkom.banksampahdelima.component.AppTopBar

@Composable
fun ArticleScreen() {
    Column(Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        // TopBar
        AppTopBar(onBackClicked = { /*TODO*/ }, midTitle = "Artikel")

        /*TODO : Change to article list and use lazy list*/
        Spacer(modifier = Modifier.height(16.dp))
        ArticleItem(
            title = "Tips mengelolah sampah organik agar lebih bermanfaat",
            category = "Blog",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Penyesuaian jadwal operasional Balai Bank Sampah Delima",
            category = "Pengumuman",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Tips mengelolah sampah organik agar lebih bermanfaat",
            category = "Blog",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Penyesuaian jadwal operasional Balai Bank Sampah Delima",
            category = "Pengumuman",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Tips mengelolah sampah organik agar lebih bermanfaat",
            category = "Blog",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Penyesuaian jadwal operasional Balai Bank Sampah Delima",
            category = "Pengumuman",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Tips mengelolah sampah organik agar lebih bermanfaat",
            category = "Blog",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Penyesuaian jadwal operasional Balai Bank Sampah Delima",
            category = "Pengumuman",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Tips mengelolah sampah organik agar lebih bermanfaat",
            category = "Blog",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
        ArticleItem(
            title = "Penyesuaian jadwal operasional Balai Bank Sampah Delima",
            category = "Pengumuman",
            date = "1 Januari 2023",
            navController = rememberNavController()
        )
    }
}