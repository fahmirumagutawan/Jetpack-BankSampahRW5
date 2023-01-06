package com.filkom.banksampahdelima.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = AppColor.Primary500,
    disabledBackgroundColor: Color = AppColor.GrayDisabled,
    rippleColor: Color = Color.White,
    enabled: Boolean = true,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Unspecified,
    shape: Shape = RoundedCornerShape(8.dp),
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    val contentHeight = remember { mutableStateOf(0.dp) }
    val contentWidth = remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current

    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = modifier
                .clip(shape)
                .size(width = contentWidth.value, height = contentHeight.value)
                .border(width = borderWidth, color = borderColor, shape = shape)
                .background(if (enabled) backgroundColor else disabledBackgroundColor)
        )

        Box(
            modifier = modifier
                .onSizeChanged {
                    contentWidth.value = with(localDensity) { it.width.toDp() }
                    contentHeight.value = with(localDensity) { it.height.toDp() }
                }
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
            contentAlignment = contentAlignment
        ) {
            content()
        }

        Box(
            modifier = modifier
                .clip(shape)
                .size(width = contentWidth.value, height = contentHeight.value)
                .clickable(
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = rippleColor),
                    onClick = onClick
                )
        )
    }
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = AppColor.Primary500,
    disabledBackgroundColor: Color = AppColor.GrayDisabled,
    rippleColor: Color = Color.White,
    enabled: Boolean = true,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Unspecified,
    shape: Shape = RoundedCornerShape(8.dp),
    contentAlignment: Alignment = Alignment.Center,
    text: String,
    textColor: Color = AppColor.White
) {
    val contentHeight = remember { mutableStateOf(0.dp) }
    val contentWidth = remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current

    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = modifier
                .clip(shape)
                .size(width = contentWidth.value, height = contentHeight.value)
                .border(width = borderWidth, color = borderColor, shape = shape)
                .background(if (enabled) backgroundColor else disabledBackgroundColor)
        )

        Box(
            modifier = modifier
                .onSizeChanged {
                    contentWidth.value = with(localDensity) { it.width.toDp() }
                    contentHeight.value = with(localDensity) { it.height.toDp() }
                }
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
            contentAlignment = contentAlignment
        ) {
            AppText(text = text, textType = TextType.Body1, color = textColor)
        }

        Box(
            modifier = modifier
                .clip(shape)
                .size(width = contentWidth.value, height = contentHeight.value)
                .clickable(
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = rippleColor),
                    onClick = onClick
                )
        )
    }
}

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    rippleColor: Color = AppColor.Black,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = rippleColor),
                onClick = onClick
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}