package com.example.advancedtipcalculator.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.advancedtipcalculator.R

// Set of Material typography styles to start with

val DancingScript = FontFamily(
    Font(R.font.dancing_script_variable_font_wght)
)
val Raleway = FontFamily(
    Font(R.font.raleway_semibold)
)

val Typography = Typography(

    h1 = TextStyle(
        fontFamily = DancingScript,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),

    body1 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)