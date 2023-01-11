package com.filkom.banksampahdelima.screen.article

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.AppTopBar
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleDetailScreen() {
    /*TODO : Change parameter to article model*/

    Scaffold(
        topBar = {
            AppTopBar(onBackClicked = { /*TODO*/ }, midTitle = "Artikel")
        }
    ) {
        Column(Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {

            // Picture
            AsyncImage(
                model = R.drawable.img_detail_article,
                contentDescription = "Article's Picture",
                placeholder = painterResource(id = R.drawable.img_detail_article),
                error = painterResource(id = R.drawable.img_detail_article),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Category
            Box(modifier = Modifier
                .wrapContentWidth()
                .background(color = AppColor.Primary500, shape = RoundedCornerShape(3.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                AppText(
                    text = "Blog",
                    textType = TextType.H5,
                    color = AppColor.White
                )
            }


            // Title
            Spacer(modifier = Modifier.height(8.dp))
            AppText(
                text = "Tips mengelolah sampah organik agar lebih bermanfaat",
                textType = TextType.H4,
                modifier = Modifier.padding(end = 24.dp)
            )

            // Date
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                AsyncImage(
                    model = R.drawable.ic_calendar,
                    contentDescription = "Calendar",
                    modifier = Modifier.size(15.dp)
                )
                AppText(
                    text = "1 Januari 2023",
                    textType = TextType.ExtraSmall,
                    color = Color.Gray
                )
            }

            // Description
            Spacer(modifier = Modifier.height(16.dp))
            AppText(
                text = "Mengolah sampah organik secara tepat dan bertanggung jawab sangat penting untuk menjaga lingkungan sehat dan bersih. Berikut adalah 3 tips yang dapat Anda gunakan untuk mengolah sampah organik dengan lebih bermanfaat: \n" +
                        "\n" +
                        " Pemanfaatan Metode Kompos. Metode kompos adalah salah satu cara terbaik untuk mengolah sampah organik. Dengan metode kompos, Anda dapat mempercepat proses dekomposisi sampah organik dan mengubahnya menjadi pupuk yang berguna untuk tanaman dan tanah. \n" +
                        "Pemanfaatan Teknologi. Teknologi dapat membantu Anda dalam mengolah sampah organik dengan lebih baik dan bermanfaat. Dengan menggunakan mesin penghancur sampah, Anda dapat mempercepat proses dekomposisi dan membuat pupuk dari sampah organik.\n" +
                        "Menggunakan Metode Reuse. Reuse atau pemanfaatan kembali adalah cara terbaik untuk mengurangi jumlah sampah yang dibuang ke lingkungan. Anda dapat melakukan hal ini dengan memperbaiki barang-barang yang rusak dan menggunakannya kembali atau menggunakan barang-barang yang masih bisa digunakan. \n" +
                        "\n" +
                        "Dengan menggunakan ketiga tips di atas, Anda dapat mengolah sampah organik dengan lebih bermanfaat. Dengan mengolah sampah organik secara tepat dan bertanggung jawab, Anda dapat membantu menjaga lingkungan sehat dan bersih.",
                textType = TextType.Body2
            )
        }
    }
}