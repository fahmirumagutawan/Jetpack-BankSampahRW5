package com.filkom.banksampahdelima.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.filkom.banksampahdelima.R

object AppType {
    fun h1() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_bold)),
        fontSize = 24.sp
    )

    fun h2() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_bold)),
        fontSize = 20.sp
    )

    fun h3() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_bold)),
        fontSize = 18.sp
    )

    fun h4() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_bold)),
        fontSize = 16.sp
    )

    fun h5() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_bold)),
        fontSize = 14.sp
    )

    fun body1() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_regular)),
        fontSize = 16.sp
    )

    fun body2() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_regular)),
        fontSize = 14.sp
    )

    fun extraSmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.opensauceone_regular)),
        fontSize = 14.sp
    )
}