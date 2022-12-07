package com.filkom.banksampahdelima.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.filkom.banksampahdelima.ui.theme.AppColor
import com.filkom.banksampahdelima.ui.theme.AppType

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppColor.Black,
    textType: TextType
) {
    Text(
        text = text,
        textAlign = textAlign,
        color = color,
        style = textType.style
    )
}

enum class TextType(
    val style:TextStyle
){
    H1(style = AppType.h1()),
    H2(style = AppType.h2()),
    H3(style = AppType.h3()),
    H4(style = AppType.h4()),
    H5(style = AppType.h5()),
    Body1(style = AppType.body1()),
    Body2(style = AppType.body2()),
    ExtraSmall(style = AppType.extraSmall())
}