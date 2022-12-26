package com.filkom.banksampahdelima.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.filkom.banksampahdelima.R
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun AppTextInputNormal(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    showWarningMessage: Boolean = false,
    warningMessage: String = "",
    label: String = "",
    placeHolder: String,
    textStyle: TextStyle = TextType.Body1.style,
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(4.dp),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = AppColor.Black,
    placeHolderColor: Color = AppColor.GrayPlaceholder,
    disabledTextColor: Color = AppColor.GrayDisabled,
    backgroundColor: Color = if (enabled) AppColor.White else AppColor.GrayDisabled,
    cursorColor: Color = AppColor.Black,
    errorCursorColor: Color = AppColor.Error,
    focusedIndicatorColor: Color = AppColor.Primary500,
    unfocusedIndicatorColor: Color = AppColor.GrayDisabled,
    disabledIndicatorColor: Color = AppColor.GrayDisabled,
    errorIndicatorColor: Color = AppColor.Error
) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        if (label.isNotEmpty()) {
            AppText(text = label, textType = TextType.Body1)
        }
        OutlinedTextField(
            modifier = modifier,
            shape = shape,
            readOnly = readOnly,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = textColor,
                disabledTextColor = disabledTextColor,
                backgroundColor = backgroundColor,
                cursorColor = cursorColor,
                errorCursorColor = errorCursorColor,
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                disabledIndicatorColor = disabledIndicatorColor,
                errorIndicatorColor = errorIndicatorColor
            ),
            placeholder = {
                AppText(
                    text = placeHolder,
                    textType = TextType.Body1,
                    color = placeHolderColor
                )
            },
            textStyle = textStyle
        )

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_alert),
                    contentDescription = "Alert icon",
                    tint = when {
                        isError -> AppColor.Error
                        else -> AppColor.GrayPlaceholder
                    }
                )
                AppText(
                    text = warningMessage,
                    textType = TextType.Body2,
                    color = when {
                        isError -> AppColor.Error
                        else -> AppColor.GrayPlaceholder
                    }
                )
            }
        }
    }
}