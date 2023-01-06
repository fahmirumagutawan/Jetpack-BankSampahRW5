package com.filkom.banksampahdelima.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppColor.White,
    onBackClicked: () -> Unit,
    midTitle: String
) {
    TopAppBar(modifier = modifier, backgroundColor = backgroundColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                }
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                AppText(text = midTitle, textType = TextType.Body1)
            }
        }
    }
}