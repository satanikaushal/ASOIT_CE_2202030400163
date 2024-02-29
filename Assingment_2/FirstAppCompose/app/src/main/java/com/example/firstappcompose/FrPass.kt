package com.example.firstappcompose

import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.firstappcompose.ui.theme.robot_bold

@Composable
fun ForgotPassword(navController: NavHostController){
    var btnColor by remember {
        mutableStateOf((R.color.login))
    }
    var constPassword by remember {
        mutableStateOf("")
    }
    var constPassword2 by remember {
        mutableStateOf("")
    }

    var contentVisiblity by remember {
        mutableStateOf(false)
    }
    AnimatedVisibility(visible = contentVisiblity,
    enter = slideInHorizontally(
        initialOffsetX = {it},
        animationSpec = tween(durationMillis = 500)
    ) + fadeIn()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(16.dp)
        ){
            Spacer(modifier = Modifier.height(155.dp))
            //        this is the password input text
            var passReal by rememberSaveable {
                mutableStateOf("")
            }
            var isPassRealDisplayed by remember{ mutableStateOf(true) }
            var Visible by remember {
                mutableStateOf(false)
            }
            var isPassRepeatDisplayed by remember{ mutableStateOf(true) }
            var borderColor by remember {
                mutableStateOf(false)
            }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, Color.Black, RoundedCornerShape(9.dp))
                        .height(57.dp)
                        .padding(horizontal = 15.dp)
                        .padding(vertical = 20.dp)
                        .drawWithContent {
                            if (isPassRealDisplayed) {
                                val hintTextSize = 14.sp
                                drawContext.canvas.nativeCanvas.drawText(
                                    "New Password",
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
                    value = passReal,
                    onValueChange = {
                        passReal = it
                        constPassword = it
                        isPassRealDisplayed = it.isEmpty()
                        if((constPassword == constPassword2)  && ((isPassRealDisplayed == false) == (isPassRepeatDisplayed == false))){
                            if((constPassword != "") && (constPassword2 != "")){
                                btnColor = (R.color.btn2)
                            }
                        }else{
                            btnColor = (R.color.login)
                        }
                        Visible = false
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = if(Visible){
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    }
                )
                Image(painter = if (Visible){ painterResource(id = R.drawable.show)
                }
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

            Spacer(modifier = Modifier.height(33.dp))
            //        this is the password input text
            var passRepeat by rememberSaveable {
                mutableStateOf("")
            }

            var Visible1 by remember {
                mutableStateOf(false)
            }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.5.dp,
                            if (!borderColor) {
                                Color.Black
                            } else {
                                Color.Red
                            }, RoundedCornerShape(9.dp)
                        )
                        .height(57.dp)
                        .padding(horizontal = 15.dp)
                        .padding(vertical = 20.dp)
                        .drawWithContent {
                            if (isPassRepeatDisplayed) {
                                val hintTextSize = 14.sp
                                drawContext.canvas.nativeCanvas.drawText(
                                    "Repeat Password",
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
                    value = passRepeat,
                    onValueChange = {
                        passRepeat = it
                        constPassword2 = it
                        isPassRepeatDisplayed = it.isEmpty()
                        if((constPassword == constPassword2)  && ((isPassRealDisplayed == false) == (isPassRepeatDisplayed == false))){
                            if((constPassword != "") && (constPassword2 != "")){
                                btnColor = (R.color.btn2)
                            }
                        }else{
                            btnColor = (R.color.login)
                        }
                        Visible1 = false
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if(Visible1){
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    }
                )
                Image(painter = if (Visible1){ painterResource(id = R.drawable.show)
                }
                else{
                    painterResource(id = R.drawable.hide)
                },
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(20.dp)
                        .clickable {
                            Visible1 = !Visible1
                        }
                )
            }


            Spacer(modifier = Modifier.height(38.dp))


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(colorResource(id = btnColor))
                    .clickable(
                        enabled = (btnColor == R.color.btn2),
                        onClick = {
                            navController.navigate(route = Screen.Confirm.Route)
                        })
            ) {
                Text(
                    text = "CONTINUE",
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
        contentVisiblity = true
    }
}

