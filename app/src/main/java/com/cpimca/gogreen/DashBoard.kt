package com.cpimca.gogreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun DashBoard(navController: NavHostController) {

    val pagerState = rememberPagerState(initialPage = 0)

    val imageSlider = listOf(
        painterResource(id = R.drawable.img_banner1),
        painterResource(id = R.drawable.img_banner2),
        painterResource(id = R.drawable.img_banner3)
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.Transparent),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_dashboard_24),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color(0xFF0141E61)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Dashboard",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp, // Adjust as needed
                    color = Color(0xFF0141E61) // Adjust color as needed
                )
            )
        }
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
            modifier = Modifier,
        ) {
            HorizontalPager(
                count = imageSlider.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .padding(top = 70.dp)
                    .height(154.dp)
                    .fillMaxWidth()
            ) { page ->

                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                val scaleValue = animateFloatAsState(
                    targetValue = 1f - pageOffset,
                    animationSpec = tween(durationMillis = 1000)
                )

                val alphaValue = animateFloatAsState(
                    targetValue = 1f - pageOffset,
                    animationSpec = tween(durationMillis = 600)
                )
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            scaleX = scaleValue.value
                            scaleY = scaleValue.value
                            alpha = alphaValue.value

                        }
                ) {
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
                inactiveColor = Color(0xFF7B87DB),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 250.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Add Worker with ID prof Mention",
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
                            .clickable { navController.navigate(Route.AddWorker) },
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
                                    painter = painterResource(id = R.drawable.addworker),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Add Worker", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "Manage Worker Update,Delete,Work Distribution",
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
                            .clickable { navController.navigate(Route.ManageWorker) },
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
                                    painter = painterResource(id = R.drawable.manageworker),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Manage Worker", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "Confirm Dustbin Request Hear",
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
                            .clickable { navController.navigate(Route.DustbinServiceAdmin) },
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
                                    painter = painterResource(id = R.drawable.dustbin),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Dustbin Service Request", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "You can see Dustbin Requests Confirm by you",
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
                            .clickable { navController.navigate(Route.DustbinServiceConfirmAdmin) },
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
                                    painter = painterResource(id = R.drawable.requestconfirm),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Confirm Dustbin Request", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "Confirm Cleaning Service Request Hear",
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
                            .clickable { navController.navigate(Route.CleaningServiceAdmin) },
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
                                    painter = painterResource(id = R.drawable.cleaningservice),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Cleaning Service Request", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "You can see Cleaning Requests Confirm by you",
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
                            .clickable { navController.navigate(Route.ConfirmService) },
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
                                    painter = painterResource(id = R.drawable.confirmservice),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Confirm Cleaning Request", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }

                Text(
                    text = "Confirm Pick-up Service Request Hear",
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
                            .clickable { navController.navigate(Route.DailyPickUpServiceAdmin) },
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
                                    painter = painterResource(id = R.drawable.pickuprequest),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Cleaning Service Request", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "You can see Pick-up Requests Confirm by you",
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
                            .clickable { navController.navigate(Route.DailyPickUpServiceConfirmAdmin) },
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
                                    painter = painterResource(id = R.drawable.confirmpickup),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Confirm Cleaning Request", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
                Text(
                    text = "you can see Complain and a feedback from a user",
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
                            .clickable { navController.navigate(Route.FeedbackAndComplainForAdmin) },
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
                                    painter = painterResource(id = R.drawable.userprofile),
                                    contentDescription = "android image",
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                            Text(
                                text = "Complain and Feedback from user", style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp // Adjust font size as needed
                                )
                            )
                        }
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 765.dp)
                .fillMaxWidth()
                .height(55.dp)
                .background(Color(0xFF0141E61)),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_logout_24),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier
                    .clickable {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate(Route.LoginScreen) {
                            popUpTo(Route.DashBoard) {
                                inclusive = true
                            }
                        }

                    },
                text = "Logout",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
        }
    }
}