package com.filkom.banksampahdelima.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.component.AppText
import com.filkom.banksampahdelima.component.TextType

@Composable
fun ProfileFeaturesItem(
    title: String,
    titleColor: Color = Color.Black,
    onClick: (() -> Unit)? = null,
) {
    Column(Modifier.fillMaxWidth()){
        Box(modifier = Modifier.fillMaxWidth()) {
            AppText(
                text = title,
                textType = TextType.Body1,
                color = titleColor,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            IconButton(onClick = { }, modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Next",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Divider(
            color = Color.LightGray,
            thickness = 0.6.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}