package com.cpimca.gogreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeFregment(navController: NavController, onItemSelected: (Int) -> Unit) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(top = 66.dp, bottom = 65.dp)
            .fillMaxSize(),
    ) {
        val pagerState = rememberPagerState(initialPage = 0)
        val imageSlider = listOf(
            painterResource(id = R.drawable.img_banner1),
            painterResource(id = R.drawable.img_banner2),
            painterResource(id = R.drawable.img_banner3)
        )
        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(2600)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount)
                )
            }
        }
        Column(
            modifier = Modifier
        ) {
            HorizontalPager(
                count = imageSlider.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .height(154.dp)
                    .fillMaxWidth()
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                val scaleValue = animateFloatAsState(
                    targetValue = 1f - pageOffset, animationSpec = tween(durationMillis = 1000)
                )
                val alphaValue = animateFloatAsState(
                    targetValue = 1f - pageOffset, animationSpec = tween(durationMillis = 600)
                )
                Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.graphicsLayer {
                    scaleX = scaleValue.value
                    scaleY = scaleValue.value
                    alpha = alphaValue.value

                }) {
                    Image(
                        painter = imageSlider[page],
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color(0xFF0141E61),
                inactiveColor = Color(0xFF0277BD),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            LazyColumn {
                item {

                    Text(
                        text = "Hello CITIZENS",
                        style = TextStyle(
                            color = Color(0xFF0141E61),
                            fontSize = 25.sp // Adjust font size as needed
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Welcome and grab our service",
                        style = TextStyle(
                            color = Color(0xFF0141E61),
                            fontSize = 15.sp // Adjust font size as needed
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Clean your area through just one click",
                        style = TextStyle(
                            color = Color(0xFF0141E61),
                            fontSize = 16.sp // Adjust font size as needed
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(100.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color(0xFF0141E61)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate(Route.CleaningService) },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(100.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.intro2),
                                        contentDescription = "android image",
                                        modifier = Modifier.fillMaxSize(),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                                Text(
                                    text = "Cleaning Service", style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp // Adjust font size as needed
                                    )
                                )
                            }
                        }
                    }
                    Text(
                        text = "Book a dustbin for your area",
                        style = TextStyle(
                            color = Color(0xFF0141E61),
                            fontSize = 16.sp // Adjust font size as needed
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(100.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color(0xFF0141E61)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate(Route.DustbinService) },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier

                                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(100.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.intro3),
                                        contentDescription = "android image",
                                        modifier = Modifier.fillMaxSize(),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                                Text(
                                    text = "Dustbin Service", style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp // Adjust font size as needed
                                    )
                                )
                            }
                        }
                    }
                    Text(
                        text = "Request for a Pick-up",
                        style = TextStyle(
                            color = Color(0xFF0141E61),
                            fontSize = 16.sp // Adjust font size as needed
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(100.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color(0xFF0141E61)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate(Route.DailyPickUpService) },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier

                                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(100.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.intro1),
                                        contentDescription = "android image",
                                        modifier = Modifier.fillMaxSize(),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.FillHeight
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                                Text(
                                    text = "Pick-up Service", style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp // Adjust font size as needed
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}