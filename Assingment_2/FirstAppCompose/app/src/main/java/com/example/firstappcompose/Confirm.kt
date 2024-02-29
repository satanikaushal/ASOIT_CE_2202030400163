package com.example.firstappcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.RepeatableSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstappcompose.ui.theme.robot_bold
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Confirmation(navController: NavHostController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        var contentVisible by remember {
            mutableStateOf(false)
        }
        
        AnimatedVisibility(visible = contentVisible,
        enter = scaleIn(
            initialScale = 0.9f,
            animationSpec = RepeatableSpec(
                repeatMode = RepeatMode.Reverse,
                iterations = 6,
                animation = tween(durationMillis = 500, easing = FastOutLinearInEasing),
                initialStartOffset = StartOffset(100)
            )
        )
        ) {
            Image(painter = painterResource(id = R.drawable.confirmimg),
                contentDescription = "",
                Modifier.size(size = 120.dp))
            Spacer(modifier = Modifier.height(15.dp))
        }
        Text(text = "Password changed successfully",
            style = TextStyle(
                fontFamily = robot_bold,
                fontSize = 12.sp
            ),
            color = colorResource(id = R.color.confirm),
            fontWeight = FontWeight.W100
        )
        
        LaunchedEffect(key1 = true){
            contentVisible = true
        }
    }

    LaunchedEffect(key1 = true ){
        delay(3000L)
        navController.navigate(Screen.Login.Route){
            popUpTo("Login"){
                inclusive = true
            }
        }
    }
}