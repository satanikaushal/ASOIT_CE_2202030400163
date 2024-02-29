package com.example.firstappcompose

import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.firstappcompose.ui.theme.lucida
import com.example.firstappcompose.ui.theme.poppins_bold
import com.example.firstappcompose.ui.theme.poppins_light
import com.example.firstappcompose.ui.theme.robot_bold
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavHostController) {
    val isSplashScreenFinished = remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = isSplashScreenFinished.value,
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 1000))
    ) {
        SplashScreen(onSplashScreenFinished = { isSplashScreenFinished.value = true }, navController)
    }
    LaunchedEffect(key1 = true){
        isSplashScreenFinished.value = true
        delay(3100)
        isSplashScreenFinished.value = false
    }
}

@Composable
fun SplashScreen(onSplashScreenFinished: () -> Unit, navController: NavController) {
    var wasVisible by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .background(colorResource(id = R.color.Splash_bg))
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "",
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(colorResource(id = R.color.Splash_bg), blendMode = androidx.compose.ui.graphics.BlendMode.Softlight)
        )
        AnimatedVisibility(visible = wasVisible,
        enter = slideInHorizontally(
            animationSpec = tween(durationMillis = 1000)
        ) + fadeIn(
            animationSpec = tween(durationMillis = 1000)
        ),
            exit = slideOutHorizontally(
                targetOffsetX = {it/2},
                animationSpec = tween(durationMillis = 500)
            ) + fadeOut(
                animationSpec = tween(durationMillis = 500)
            )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "The Happy Shopper",
                style = TextStyle(
                    fontFamily = lucida,
                    fontSize = 50.sp
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        LaunchedEffect(key1 = true){
            delay(100)
            wasVisible = true
            delay(2500)
            wasVisible = false
        }
    }

    LaunchedEffect(true) {
        delay(3000L)
        navController.navigate(Screen.Login.Route){
            popUpTo(Screen.Home.Route){
                inclusive = true
            }
        }
        onSplashScreenFinished()
    }
}

@Composable
fun Login(navController: NavHostController) {
    var wasPasswordValid by remember {
        mutableStateOf(false)
    }
   var contentVisible by remember {
       mutableStateOf(false)
   }

    AnimatedVisibility(visible = contentVisible,
    enter = slideInHorizontally(
        initialOffsetX = {-it},
        animationSpec = tween(durationMillis = 500)
    ) + fadeIn()) {
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(text = "Welcome Back",
                style = TextStyle(
                    fontFamily = poppins_bold,
                    fontSize = 21.sp
                )
            )
            Text(text = "login to your account",
                style = TextStyle(
                    fontFamily = poppins_light,
                    fontSize = 12.sp
                ),
                color = colorResource(id = R.color.light),
                modifier = Modifier.padding(0.dp)
            )
            Image(painter = painterResource(id = R.drawable.google), contentDescription = null,
                modifier = Modifier
                    .padding(15.dp)
                    .size(45.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Divider(
                    modifier = Modifier.weight(0.5f),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.login)
                )
                Text(text = "Or login with",
                    style = TextStyle(
                        fontFamily = poppins_light,
                        fontSize = 12.sp
                    ),
                    color = colorResource(id = R.color.light),
                    modifier = Modifier.padding(horizontal = 17.dp))
                Divider(
                    modifier = Modifier.weight(0.5f),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.login)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
            ) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(6.dp))
                        .background(colorResource(id = R.color.btn1Bg))
                        .weight(1f)

                        .border(1.5.dp, colorResource(id = R.color.btn2), RoundedCornerShape(6.dp))
                ) {
                    Text(text = "LOG IN",
                        style = TextStyle(
                            fontFamily = robot_bold,
                            fontSize = 12.sp
                        ),
                        color = colorResource(id = R.color.white))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(route = Screen.Register.Route)
                        }
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(6.dp))
                        .weight(1f)
                        .border(1.dp, colorResource(id = R.color.btn2), RoundedCornerShape(6.dp))
                ) {
                    Text(text = "REGISTER",
                        style = TextStyle(
                            fontFamily = robot_bold,
                            fontSize = 12.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(23.dp))

            var email:String by remember {
                mutableStateOf("")
            }
            val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            var isEmailValid by remember { mutableStateOf(false) }
            var isHintDisplayed2 by remember{ mutableStateOf(true) }
            var borderRed by remember {
                mutableStateOf(false)
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp,
                        if (borderRed) {
                            Color.Red
                        } else {
                            Color.Black
                        }, RoundedCornerShape(9.dp)
                    )
                    .height(44.dp)
                    .padding(12.dp)
                    .drawWithContent {
                        if (isHintDisplayed2) {
                            val hintTextSize = 14.sp
                            drawContext.canvas.nativeCanvas.drawText(
                                "Enter email address",
                                0f,
                                hintTextSize.toPx(),
                                Paint()
                                    .apply {
                                        color = Color.LightGray.toArgb()
                                        textSize = hintTextSize.toPx()
                                        typeface = Typeface.DEFAULT
                                    }
                            )
                        }
                        drawContent()
                    },
                value = email,
                onValueChange = {it ->email = it
                    isEmailValid = emailRegex.matches(email)
                    isHintDisplayed2 = it.isEmpty()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(23.dp))

            var password by remember {
                mutableStateOf("")
            }
            var isHintDisplayed by remember{ mutableStateOf(true) }

            var Visible by remember {
                mutableStateOf(false)
            }
            var borderRed2 by remember {
                mutableStateOf(false)
            }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.5.dp,
                            if (borderRed2) {
                                Color.Red
                            } else {
                                Color.Black
                            }, RoundedCornerShape(9.dp)
                        )
                        .height(44.dp)
                        .padding(horizontal = 12.dp)
                        .padding(vertical = 12.dp)
                        .drawWithContent {
                            if (isHintDisplayed) {
                                val hintTextSize = 14.sp
                                drawContext.canvas.nativeCanvas.drawText(
                                    "Enter password",
                                    0f,
                                    hintTextSize.toPx(),
                                    Paint()
                                        .apply {
                                            color = Color.LightGray.toArgb()
                                            textSize = hintTextSize.toPx()
                                            typeface = Typeface.DEFAULT
                                        }
                                )
                            }
                            drawContent()
                        },
                    singleLine = true,
                    value = password,
                    onValueChange = {
                        password = it
                        isHintDisplayed = password.isEmpty()
                        wasPasswordValid = it.isNotEmpty()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if(Visible){
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    }
                )

                Image(painter = if (Visible){ painterResource(id = R.drawable.show)}
                else{
                    painterResource(id = R.drawable.hide)
                },
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(20.dp)
                        .clickable {
                            Visible = !Visible
                        }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontFamily = poppins_light,
                        fontSize = 12.sp
                    ),
                    color = colorResource(id = R.color.light),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screen.ForgetPassword.Route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(colorResource(id = R.color.btn2))
                    .clickable {
                        if (isEmailValid == false) {
                            borderRed = true
                        } else {
                            borderRed = false
                        }

                        if (wasPasswordValid == true) {
                            borderRed2 = false
                        } else {
                            borderRed2 = true
                        }

                        if (isEmailValid && wasPasswordValid){
                            navController.navigate(Screen.Main.Route){
                                popUpTo(Screen.Login.Route){
                                    inclusive = true
                                }
                            }
                        }
                    }
            ) {
                Text(
                    text = "LOG IN",
                    style = TextStyle(
                        fontFamily = robot_bold,
                        fontSize = 12.sp
                    ),
                    color = colorResource(id = R.color.white),
                )
            }
        }
    }

    LaunchedEffect(key1 = true){
        contentVisible = true
    }
}




