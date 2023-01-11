package com.filkom.banksampahdelima.screen.article

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun ArticleItem(
    title: String,
    category: String,
    date: String,
    picture_url: String? = null,
    navController: NavController,
) {

    /*TODO : Change parameter to article model*/
    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                /* TODO : Navigate to article's detail*/
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Picture
            AsyncImage(
                model = picture_url,
                contentDescription = "Article's Picture",
                placeholder = painterResource(id = R.drawable.img_item_article),
                error = painterResource(id = R.drawable.img_item_article),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Category, Title, Date
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp)
            ) {

                // Category
                Box(modifier = Modifier
                    .wrapContentWidth()
                    .background(color = AppColor.Primary500, shape = RoundedCornerShape(3.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    AppText(
                        text = category,
                        textType = TextType.H5,
                        color = AppColor.White
                    )
                }

                // Title
                Spacer(modifier = Modifier.height(8.dp))
                AppText(
                    text = title,
                    textType = TextType.H5
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
                        text = date,
                        textType = TextType.ExtraSmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}