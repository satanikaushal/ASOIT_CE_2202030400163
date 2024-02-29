package com.example.firstappcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Slider(pageCount : Int = 0, navController: NavHostController){
    fun getFunctionsList(): List<@Composable () -> Unit> {
        return listOf({ Login(navController = navController) }, { Register(navController = navController) })
    }
    val screen = getFunctionsList()
    val pagerState = rememberPagerState(initialPage = pageCount)

    Column(modifier = Modifier
        .fillMaxSize())
    {
        HorizontalPager(
            count = screen.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {page ->
            screen[page]()
        }
    }

}