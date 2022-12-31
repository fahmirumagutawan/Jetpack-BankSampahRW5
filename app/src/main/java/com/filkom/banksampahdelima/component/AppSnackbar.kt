package com.filkom.banksampahdelima.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.filkom.banksampahdelima.ui.theme.AppColor

@Composable
fun AppSnackbar(hostState: SnackbarHostState) {
    SnackbarHost(hostState = hostState) {
        Snackbar(
            snackbarData = it,
            shape = RoundedCornerShape(4.dp),
            backgroundColor = AppColor.GrayPlaceholder,
            contentColor = AppColor.White,
            actionColor = AppColor.Primary500
        )
    }
}