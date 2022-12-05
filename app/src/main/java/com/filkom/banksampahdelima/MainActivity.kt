package com.filkom.banksampahdelima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.filkom.banksampahdelima.ui.theme.BankSampahRW5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankSampahRW5Theme {

            }
        }
    }
}