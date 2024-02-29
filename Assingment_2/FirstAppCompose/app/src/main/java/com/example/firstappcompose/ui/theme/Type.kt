package com.example.firstappcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.firstappcompose.R

val poppins_bold = FontFamily(Font(R.font.poppins_bold))

val poppins_light = FontFamily(Font(R.font.poppins_light))

val robot_bold = FontFamily(Font(R.font.roboto_bold))

val lucida = FontFamily(Font(R.font.splash_lucida))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = poppins_bold,
        fontSize = 21.sp
    ),
    titleSmall = TextStyle(
        fontFamily = poppins_light,
        fontSize = 12.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robot_bold,
        fontSize = 12.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = lucida,
        fontSize = 50.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)