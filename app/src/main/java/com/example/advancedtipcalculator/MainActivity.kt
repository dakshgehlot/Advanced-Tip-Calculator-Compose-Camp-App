package com.example.advancedtipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.advancedtipcalculator.data.TipHistoryItem
import com.example.advancedtipcalculator.data.TipHistoryViewModel
import com.example.advancedtipcalculator.ui.HistoryListItem
import com.example.advancedtipcalculator.ui.theme.CustomTipCalculatorTheme

/*

Hex Codes:
    Light Green: 0xFFCEF5E6
    Medium Green: 0xFF0D8A53
    Dark Green: 0xFF08502F

 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AdvancedTipCalculator(DatabaseTipViewModel = TipHistoryViewModel(application = application))
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CustomTipCalculatorTheme(darkTheme = true) {
        HistoryListItem(listItem = TipHistoryItem(0, 1.0, 2.0, 3.0))
    }
}
