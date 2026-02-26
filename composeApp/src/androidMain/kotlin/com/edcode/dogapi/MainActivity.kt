package com.edcode.dogapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.edcode.dogapi.presentation.screens.App
import com.edcode.dogapi.presentation.screens.App2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App2()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}