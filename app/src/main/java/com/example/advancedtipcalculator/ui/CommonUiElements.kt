package com.example.advancedtipcalculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColoringBelowAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFFCEF5E6),
                RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(25.dp))
    }
}