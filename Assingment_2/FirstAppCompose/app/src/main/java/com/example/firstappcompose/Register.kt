package com.example.firstappcompose

import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.firstappcompose.ui.theme.poppins_light
import com.example.firstappcompose.ui.theme.robot_bold

@Composable
fun Register(navController: NavHostController){
    var contentVisible by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(visible = contentVisible,
    enter = slideInHorizontally(
        initialOffsetX = {it},
        animationSpec = tween(durationMillis = 500)
    ) + fadeIn()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(30.dp)
        ) {

            Spacer(modifier = Modifier.height(17.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
            ) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.Login.Route) {
                                popUpTo(Screen.Login.Route) {
                                    inclusive = true
                                }
                            }
                        }
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(6.dp))
                        .weight(1f)
                        .border(1.5.dp, Color.Black, RoundedCornerShape(6.dp))
                ) {
                    Text(text = "LOG IN",
                        style = TextStyle(
                            fontFamily = robot_bold,
                            fontSize = 12.sp
                        ),
                        color = Color.Black)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(6.dp))
                        .weight(1f)
                        .background(color = colorResource(id = R.color.register2))
                        .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                ) {
                    Text(text = "REGISTER",
                        style = TextStyle(
                            fontFamily = robot_bold,
                            fontSize = 12.sp
                        ),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(41.dp))

            var name by rememberSaveable {
                mutableStateOf("")
            }
            var isNameDisplayed by remember{ mutableStateOf(true) }
            var nameBorder by remember {
                mutableStateOf(false)
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp,
                        if (nameBorder) {
                            Color.Red
                        } else {
                            Color.Black
                        }, RoundedCornerShape(9.dp)
                    )
                    .height(44.dp)
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp)
                    .drawWithContent {
                        if (isNameDisplayed) {
                            val hintTextSize = 14.sp
                            drawContext.canvas.nativeCanvas.drawText(
                                "Name",
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
                value = name,
                onValueChange = {
                    name = it
                    isNameDisplayed = it.isEmpty()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(14.dp))


//        this is the email input text
            var email1 by rememberSaveable {
                mutableStateOf("")
            }
            var isEmailDisplayed by remember{ mutableStateOf(true) }
            val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            var isEmailValid by remember { mutableStateOf(false) }
            var emailBorder by remember {
                mutableStateOf(false)
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp,
                        if (emailBorder) {
                            Color.Red
                        } else {
                            Color.Black
                        }, RoundedCornerShape(9.dp)
                    )
                    .height(44.dp)
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp)
                    .drawWithContent {
                        if (isEmailDisplayed) {
                            val hintTextSize = 14.sp
                            drawContext.canvas.nativeCanvas.drawText(
                                "Email",
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
                value = email1,
                onValueChange = {
                    email1 = it
                    isEmailValid = emailRegex.matches(email1)
                    isEmailDisplayed = it.isEmpty()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(14.dp))


//        this is the phone number input text
            var phone by rememberSaveable {
                mutableStateOf("")
            }
            var isPhoneDisplayed by remember{ mutableStateOf(true) }

            var phoneBorder by remember {
                mutableStateOf(false)
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp, if (phoneBorder) {
                            Color.Red
                        } else {
                            Color.Black
                        }, RoundedCornerShape(9.dp)
                    )
                    .height(44.dp)
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp)
                    .drawWithContent {
                        if (isPhoneDisplayed) {
                            val hintTextSize = 14.sp
                            drawContext.canvas.nativeCanvas.drawText(
                                "Phone number",
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
                value = phone,
                onValueChange = {
                    phone = it.take(10)
                    isPhoneDisplayed = it.isEmpty()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(14.dp))


//        this is the address input section
            var address by rememberSaveable {
                mutableStateOf("")
            }
            var isAddressDisplayed by remember{ mutableStateOf(true) }

            var addressBorder by remember {
                mutableStateOf(false)
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp, if (addressBorder) {
                            Color.Red
                        } else {
                            Color.Black
                        }, RoundedCornerShape(9.dp)
                    )
                    .height(44.dp)
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp)
                    .drawWithContent {
                        if (isAddressDisplayed) {
                            val hintTextSize = 14.sp
                            drawContext.canvas.nativeCanvas.drawText(
                                "Address",
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
                value = address,
                onValueChange = {
                    address = it
                    isAddressDisplayed = it.isEmpty()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(14.dp))


//        this is the password input text
            var pass1 by rememberSaveable {
                mutableStateOf("")
            }
            var Visible by remember {
                mutableStateOf(false)
            }

            var passBorder by remember {
                mutableStateOf(false)
            }

            var isPassDisplayed by remember{ mutableStateOf(true) }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.5.dp, if (passBorder) {
                                Color.Red
                            } else {
                                Color.Black
                            }, RoundedCornerShape(9.dp)
                        )
                        .height(44.dp)
                        .padding(horizontal = 12.dp)
                        .padding(vertical = 12.dp)
                        .drawWithContent {
                            if (isPassDisplayed) {
                                val hintTextSize = 14.sp
                                drawContext.canvas.nativeCanvas.drawText(
                                    "Password",
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
                    value = pass1,
                    onValueChange = {
                        pass1 = it
                        isPassDisplayed = it.isEmpty()
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

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(colorResource(id = R.color.btn2))
                    .clickable {
                        if (isNameDisplayed) {
                            nameBorder = true
                        } else {
                            nameBorder = false
                        }

                        if (isEmailValid == false) {
                            emailBorder = true
                        } else {
                            emailBorder = false
                        }

                        if (isPhoneDisplayed == true) {
                            phoneBorder = true
                        } else {
                            phoneBorder = false
                        }

                        if (isAddressDisplayed == true) {
                            addressBorder = true
                        } else {
                            addressBorder = false
                        }

                        if (isPassDisplayed == true) {
                            passBorder = true
                        } else {
                            passBorder = false
                        }
                    }
            ) {
                Text(
                    text = "NEXT",
                    style = TextStyle(
                        fontFamily = robot_bold,
                        fontSize = 12.sp
                    ),
                    color = colorResource(id = R.color.white),

                    )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Divider(
                    modifier = Modifier.weight(0.5f),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.login)
                )
                Text(text = "Or register with",
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

            Image(painter = painterResource(id = R.drawable.googeltrans), contentDescription = null,
                modifier = Modifier
                    .padding(15.dp)
                    .size(45.dp)
            )

        }
    }
    LaunchedEffect(key1 = true){
        contentVisible = true
    }
}