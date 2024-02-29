package com.example.firstappcompose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AllCategories(navController: NavHostController) {
    var visiblity by remember {
        mutableStateOf(false)
    }
    BackHandler(onBack = {
        visiblity = false
        navController.popBackStack()
    })
    AnimatedVisibility(visible = visiblity,
    enter = slideInHorizontally(
        animationSpec = tween(durationMillis = 900,easing = EaseOutBounce),
        initialOffsetX = {fullWidth -> -fullWidth }
    ),
        exit = shrinkHorizontally()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6F8))
            .padding(20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "All Categories",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.segoe_ui_bold)),
                        fontSize = 12.sp
                    )
                )
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back arrow", modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                visiblity = false
                                navController.popBackStack()
                            }
                        )
                    }
                    .indication(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    )
                    , tint = Color(0xffFF6108)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween){
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 7.dp)
                        .fillMaxWidth(0.2f),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
//                item 1
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFFFFC076),
                                            Color(0xFF9C3C1E)
                                        )
                                    ), shape = CircleShape
                                )
                                .size(55.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.zondicons_apparel), contentDescription = "apparel icon")
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Clothes",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }

//                item 2
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    color = Color(0xff18CDCA), shape = CircleShape
                                )
                                .size(55.dp)
                                .padding(bottom = 15.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Image(painter = painterResource(id = R.drawable.beauty), contentDescription = "apparel icon", modifier = Modifier.scale(2.8f))
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Cosmetics",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }

//                item 3
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFFC50920),
                                            Color(0xFF030303)
                                        )
                                    ), shape = CircleShape
                                )
                                .size(55.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.emojione_high_heeled_shoe), contentDescription = "apparel icon")
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Shoes",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }

                    //item 4
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFFFF7676),
                                            Color(0xFFFFAE4E)
                                        )
                                    ), shape = CircleShape
                                )
                                .size(55.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.capacitorpng),
                                contentDescription = "apparel icon",
                                modifier = Modifier.scale(3f))
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Electronics",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }


                    //item 5
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFFE6B15C),
                                            Color(0xFFFFF84E)
                                        )
                                    ), shape = CircleShape
                                )
                                .size(55.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(imageVector = Icons.Default.Home, contentDescription = "home",
                                modifier = Modifier.scale(1.4f)
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Electronics",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }


//                item 6
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFF77A5F8),
                                            Color(0xFFD5A3FF)
                                        )
                                    ), shape = CircleShape
                                )
                                .size(55.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.sofa),
                                contentDescription = "apparel icon",
                                modifier = Modifier.scale(3f))
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Furniture",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }

                    //item 7
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = CircleShape)
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFF77A5F8),
                                            Color(0xFFD5A3FF)
                                        )
                                    ), shape = CircleShape
                                )
                                .size(55.dp)
                                .padding(bottom = 10.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Image(painter = painterResource(id = R.drawable.statiionary),
                                contentDescription = "apparel icon",
                                modifier = Modifier.scale(2.5f))
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Stationary",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFACB3BF)
                            )
                        )
                    }





                }


                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.8f)
                ){
                    Column {
                        val mens = listOf("T-shirts","Shirts","Hoods","Jeans","Jackets","Underwear's","Sweaters","Socks & Ties")
                        Text(text = "Men's Clothing",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 14.sp,
                                color = Color(0xffACB3BF)
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                        ) {
                            mens.forEach { name ->
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 9.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = name, style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                        color = Color(0xffACB3BF),
                                        fontSize = 12.sp
                                    ))

                                    Box(modifier = Modifier
                                        .background(Color(0xffC4C4C4), shape = CircleShape)
                                        .size(17.dp), contentAlignment = Alignment.Center){
                                        Icon(imageVector = Icons.Default.ArrowForward
                                            , contentDescription = null, modifier = Modifier.size(15.dp))
                                    }
                                }
                                Divider()
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        val mens1 = listOf("Blouse","Shirts","Office wears","Jeans","Jackets","Underwear's","lingerie","Dresses")
                        Text(text = "Women's Clothing",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                fontSize = 14.sp,
                                color = Color(0xffACB3BF)
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        ) {
                            Spacer(modifier = Modifier.height(5.dp))
                            mens1.forEach { name ->
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 9.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = name, style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                        color = Color(0xffACB3BF),
                                        fontSize = 12.sp
                                    ))

                                    Box(modifier = Modifier
                                        .background(Color(0xffC4C4C4), shape = CircleShape)
                                        .size(17.dp), contentAlignment = Alignment.Center){
                                        Icon(imageVector = Icons.Default.ArrowForward
                                            , contentDescription = null, modifier = Modifier.size(15.dp))
                                    }
                                }
                                Divider()
                            }
                        }
                    }

                }
            }
        }
    }

    LaunchedEffect(key1 = true){
        visiblity = true
    }
}