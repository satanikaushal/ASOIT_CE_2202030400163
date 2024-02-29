package com.example.firstappcompose

import android.annotation.SuppressLint
import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Main(navController: NavHostController) {
    var currentScreen by remember {
        mutableStateOf(0)
    }
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Cart", "Favorite", "Profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.ShoppingCart, Icons.Default.FavoriteBorder, Icons.Default.AccountBox)
    var visible by remember {
        mutableStateOf(false)
    }

            AnimatedVisibility(visible = visible,
                enter = fadeIn()
            ) {
                Scaffold(
                    bottomBar ={
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            items.forEachIndexed { index,item ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    selectedItem = index
                                                    currentScreen = index
                                                    visible = false
                                                }
                                            )
                                        }
                                        .indication(
                                            interactionSource = remember {
                                                MutableInteractionSource()
                                            },
                                            indication = null
                                        )
                                ) {
                                    Icon(imageVector = icons[index], contentDescription = item,
                                        tint = if (selectedItem == index) Color(0xffFF6108) else Color.Black ,
                                    )
                                    Text(text = item,
                                        style = TextStyle(
                                            color = if (selectedItem == index) Color(0xffFF6108) else Color.Black
                                        )
                                    )
                                }
                            }
                        }
                    },
                    content = {
                            paddingValues ->
                       if (currentScreen == 0){
                           AnimatedVisibility(visible = visible,
                               enter = slideInHorizontally(
                                   animationSpec = tween(durationMillis = 900),
                                   initialOffsetX = {fullWidth -> fullWidth }
                               ),
                               exit = slideOutHorizontally(
                                   animationSpec = tween(durationMillis = 1500),
                                   targetOffsetX = {fullWidth -> fullWidth }
                               )) {
                               Box(modifier = Modifier
                                   .fillMaxSize()
                                   .padding(paddingValues)) {
                                   Column (
                                       modifier = Modifier
                                           .fillMaxSize()
                                           .background(color = Color(0xFFF5F6F8))
                                           .padding(4.dp)
                                   ){
                                       Spacer(modifier = Modifier.height(20.dp))
                                       Row(modifier = Modifier.fillMaxWidth(),
                                           horizontalArrangement = Arrangement.SpaceBetween) {
                                           IconButton(onClick = { /*TODO*/ }) {
                                               Icon(painter = painterResource(id = R.drawable.__bar_menu), contentDescription = null, modifier = Modifier.size(20.dp) )
                                           }
                                           IconButton(onClick = { /*TODO*/ }) {
                                               Icon(painter = painterResource(id = R.drawable.bell), contentDescription = null, modifier = Modifier.size(25.dp) )
                                           }
                                       }
                                       Text(modifier = Modifier.padding(horizontal = 10.dp),text = "Hi, Huey!",
                                           style = TextStyle(
                                               fontFamily = FontFamily(Font(R.font.inter_medium))
                                           ),
                                           fontSize =17.sp
                                       )
                                       Spacer(modifier = Modifier.height(18.dp))
                                       var text by remember {
                                           mutableStateOf("")
                                       }
                                       var isHintDisplayed by remember {
                                           mutableStateOf(true)
                                       }

                                       Row(
                                           modifier = Modifier
                                               .fillMaxWidth()
                                               .padding(horizontal = 8.dp),
                                           horizontalArrangement = Arrangement.SpaceBetween
                                       ) {
                                           Box(modifier = Modifier
                                               .fillMaxWidth(0.76f)
                                               .clip(RoundedCornerShape(15))
                                               .background(Color.White)
                                               .border(
                                                   1.dp,
                                                   color = Color(0xFFE8E0E0),
                                                   shape = RoundedCornerShape(15)
                                               ),
                                               contentAlignment = Alignment.CenterStart){
                                               IconButton(onClick = { /*TODO*/ }) {
                                                   Image(painter = painterResource(id = R.drawable.search_icon_svg), contentDescription = null)
                                               }
                                               BasicTextField(
                                                   modifier = Modifier
                                                       .fillMaxWidth()
                                                       .height(57.dp)
                                                       .padding(start = 53.dp, end = 10.dp)
                                                       .padding(vertical = 15.dp)
                                                       .drawWithContent {
                                                           if (isHintDisplayed) {
                                                               val hintTextSize = 20.sp
                                                               drawContext.canvas.nativeCanvas.drawText(
                                                                   "Search",
                                                                   0f,
                                                                   hintTextSize.toPx(),
                                                                   Paint()
                                                                       .apply {
                                                                           color =
                                                                               Color.LightGray.toArgb()
                                                                           textSize =
                                                                               hintTextSize.toPx()
                                                                           typeface = Typeface.DEFAULT
                                                                       }
                                                               )
                                                           }
                                                           drawContent()
                                                       },
                                                   singleLine = true,
                                                   value = text,
                                                   onValueChange = {
                                                       text = it
                                                       isHintDisplayed = text.isEmpty()
                                                   },
                                                   keyboardOptions = KeyboardOptions(
                                                       keyboardType = KeyboardType.Password,
                                                       imeAction = ImeAction.Done
                                                   ),
                                                   textStyle = TextStyle(
                                                       fontSize = 20.sp
                                                   )
                                               )
                                           }
                                           Image(painter = painterResource(id = R.drawable.filter_box), contentDescription = null, modifier = Modifier.size(57.dp))
                                       }
                                       Spacer(modifier = Modifier.height(14.dp))


                                       Text(text = "Categories",
                                           style = TextStyle(
                                               fontFamily = FontFamily(Font(R.font.segoe_ui_bold)),
                                               fontSize = 17.sp
                                           ),
                                           modifier = Modifier.padding(start = 10.dp)
                                       )


                                       Spacer(modifier = Modifier.height(15.dp))


                                       Box(
                                           modifier = Modifier
                                               .fillMaxWidth()
                                               .padding(horizontal = 10.dp),
                                           contentAlignment = Alignment.CenterEnd
                                       ) {

                                           Row(
                                               modifier = Modifier
                                                   .padding(end = 30.dp)
                                                   .fillMaxWidth(1f)
                                                   .horizontalScroll(state = rememberScrollState()),
                                               horizontalArrangement = Arrangement.spacedBy(35.dp),
                                           ) {

//                                item 1
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
                                               Column(
                                                   verticalArrangement = Arrangement.Center,
                                                   horizontalAlignment = Alignment.CenterHorizontally
                                               ) {
                                                   Box(
                                                       modifier = Modifier
                                                           .shadow(4.dp, shape = CircleShape)
                                                           .background(
                                                               color = Color(0xff18CDCA),
                                                               shape = CircleShape
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

//                                item 3
                                               Column(
                                                   verticalArrangement = Arrangement.Center,
                                                   horizontalAlignment = Alignment.CenterHorizontally
                                               ) {
                                                   Box(
                                                       modifier = Modifier
                                                           .shadow(4.dp, shape = CircleShape)
                                                           .background(
                                                               color = Color(0xffC50920),
                                                               shape = CircleShape
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

//                                item 4
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


//                                item 5
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



//                                item 5
                                               Column(
                                                   verticalArrangement = Arrangement.Center,
                                                   horizontalAlignment = Alignment.CenterHorizontally
                                               ) {
                                                   Box(
                                                       modifier = Modifier
                                                           .shadow(4.dp, shape = CircleShape)
                                                           .background(
                                                               color = Color(0xFFF5F6F8),
                                                               shape = CircleShape
                                                           )
                                                           .size(5.dp),
                                                       contentAlignment = Alignment.Center
                                                   ) {

                                                   }
                                                   Spacer(modifier = Modifier.height(5.dp))
                                                   Text(text = "",
                                                       style = TextStyle(
                                                           fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                           fontSize = 13.sp,
                                                           fontWeight = FontWeight.SemiBold,
                                                           color = Color(0xFFACB3BF)
                                                       )
                                                   )
                                               }
                                           }

                                           Column(
                                               verticalArrangement = Arrangement.Center,
                                               horizontalAlignment = Alignment.CenterHorizontally,
                                               modifier = Modifier
                                                   .pointerInput(Unit) {
                                                       detectTapGestures(
                                                           onTap = {
                                                               navController.navigate(Screen.AllCategories.Route)
                                                               visible = false
                                                           }
                                                       )
                                                   }
                                                   .indication(
                                                       interactionSource = remember {
                                                           MutableInteractionSource()
                                                       },
                                                       indication = null
                                                   )
                                           ) {
                                               Box(
                                                   modifier = Modifier
                                                       .shadow(7.dp, shape = CircleShape)
                                                       .background(
                                                           color = Color.White, shape = CircleShape
                                                       )
                                                       .size(56.dp)
                                                       .scale(1.2f),
                                                   contentAlignment = Alignment.Center
                                               ) {
                                                   Image(painter = painterResource(id = R.drawable.right_arrow), contentDescription = "apparel icon")
                                               }
                                               Spacer(modifier = Modifier.height(5.dp))
                                               Text(text = "See all",
                                                   style = TextStyle(
                                                       fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                       fontSize = 13.sp,
                                                       fontWeight = FontWeight.SemiBold,
                                                       color = Color(0xFFACB3BF)
                                                   ),
                                                   modifier = Modifier
                                                       .background(color = Color(0xFFF5F6F8))
                                                       .padding(horizontal = 12.dp)
                                               )
                                           }
                                       }


                                       Spacer(modifier = Modifier.height(14.dp))


                                       Text(text = "New Arrivals",
                                           style = TextStyle(
                                               fontFamily = FontFamily(Font(R.font.segoe_ui_bold)),
                                               fontSize = 17.sp
                                           ),
                                           modifier = Modifier.padding(start = 10.dp)
                                       )

                                       Spacer(modifier = Modifier.height(17.dp))

                                       Box(modifier = Modifier.fillMaxWidth()) {
                                           Image(painter = painterResource(id = R.drawable.home_bg_png),
                                               contentDescription = null,
                                               modifier = Modifier
                                                   .padding(horizontal = 6.dp)
                                                   .fillMaxWidth()
                                                   .height(190.dp))
                                       }

                                       Spacer(modifier = Modifier.height(5.dp))

                                       Row(
                                           modifier = Modifier
                                               .padding(horizontal = 10.dp)
                                               .fillMaxWidth(),
                                           horizontalArrangement = Arrangement.SpaceBetween
                                       ) {
                                           ElevatedCard(
                                               modifier = Modifier
                                                   .width(100.dp)
                                                   .height(130.dp),
                                               elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                               content = {
                                                   Column(
                                                       verticalArrangement = Arrangement.Center,
                                                       horizontalAlignment = Alignment.CenterHorizontally,
                                                       modifier = Modifier.padding(5.dp)
                                                   ) {
                                                       Row(modifier = Modifier.fillMaxWidth(),
                                                           horizontalArrangement = Arrangement.End,) {
                                                           Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "heart", tint = Color.Red,
                                                               modifier = Modifier.size(20.dp))

                                                       }
                                                       Image(painter = painterResource(id = R.drawable.backpack_png6354_1),
                                                           contentDescription = "backpack img",
                                                           modifier = Modifier.size(60.dp))
                                                       Spacer(modifier = Modifier.height(7.dp))
                                                       Text(text = "Adidas Backpack",
                                                           style = TextStyle(
                                                               fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                               fontWeight = FontWeight.SemiBold,
                                                               fontSize = 11.sp
                                                           )
                                                       )
                                                       Text(text = "$100.00",
                                                           style = TextStyle(
                                                               fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                               fontWeight = FontWeight.SemiBold,
                                                               fontSize = 10.sp
                                                           )
                                                       )
                                                   }
                                               },
                                               colors = CardDefaults.cardColors(containerColor = Color.White)
                                           )


                                           ElevatedCard(
                                               modifier = Modifier
                                                   .width(100.dp)
                                                   .height(130.dp),
                                               elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                               content = {
                                                   Column(
                                                       verticalArrangement = Arrangement.Center,
                                                       horizontalAlignment = Alignment.CenterHorizontally,
                                                       modifier = Modifier.padding(5.dp)
                                                   ) {
                                                       Row(modifier = Modifier.fillMaxWidth(),
                                                           horizontalArrangement = Arrangement.End,) {
                                                           Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "heart", tint = Color.Red,
                                                               modifier = Modifier.size(20.dp))

                                                       }
                                                       Image(painter = painterResource(id = R.drawable.scarf_png48),
                                                           contentDescription = "backpack img",
                                                           modifier = Modifier
                                                               .size(60.dp)
                                                               .scale(1.2f))
                                                       Spacer(modifier = Modifier.height(7.dp))
                                                       Text(text = "Red Scarf",
                                                           style = TextStyle(
                                                               fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                               fontWeight = FontWeight.SemiBold,
                                                               fontSize = 11.sp
                                                           )
                                                       )
                                                       Text(text = "$120.00",
                                                           style = TextStyle(
                                                               fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                               fontWeight = FontWeight.SemiBold,
                                                               fontSize = 10.sp
                                                           )
                                                       )
                                                   }
                                               },
                                               colors = CardDefaults.cardColors(containerColor = Color.White)
                                           )



                                           ElevatedCard(
                                               modifier = Modifier
                                                   .width(100.dp)
                                                   .height(130.dp),
                                               elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                               content = {
                                                   Column(
                                                       verticalArrangement = Arrangement.Center,
                                                       horizontalAlignment = Alignment.CenterHorizontally,
                                                       modifier = Modifier.padding(5.dp)
                                                   ) {
                                                       Row(modifier = Modifier.fillMaxWidth(),
                                                           horizontalArrangement = Arrangement.End,) {
                                                           Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "heart", tint = Color.Red,
                                                               modifier = Modifier.size(20.dp))

                                                       }
                                                       Image(painter = painterResource(id = R.drawable.showimg),
                                                           contentDescription = "backpack img",
                                                           modifier = Modifier
                                                               .size(60.dp)
                                                               .scale(1.5f))
                                                       Spacer(modifier = Modifier.height(7.dp))
                                                       Text(text = "Nike Air Max",
                                                           style = TextStyle(
                                                               fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                               fontWeight = FontWeight.SemiBold,
                                                               fontSize = 11.sp
                                                           )
                                                       )
                                                       Text(text = "$100.00",
                                                           style = TextStyle(
                                                               fontFamily = FontFamily(Font(R.font.segoe_ui)),
                                                               fontWeight = FontWeight.SemiBold,
                                                               fontSize = 10.sp
                                                           )
                                                       )
                                                   }
                                               },
                                               colors = CardDefaults.cardColors(containerColor = Color.White)
                                           )
                                       }
                                   }
                               }
                           }
                           LaunchedEffect(key1 = true){
                               visible = true
                           }
                       }


                        if (currentScreen == 3){
                            AnimatedVisibility(visible = visible) {
                                Box(modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                    Text(text = "Profile Screen")
                                }
                            }
                            LaunchedEffect(key1 = true){
                                visible = true
                            }
                        }
                    }
                )
            }

    LaunchedEffect(key1 = true){
        visible = true
    }
}