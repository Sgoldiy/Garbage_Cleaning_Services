package com.cpimca.gogreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackAndComplainForAdmin(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .background(Color(0xFF0141E61))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        navController.popBackStack(Route.FeedbackAndComplainForAdmin, inclusive = true)
                    }
            )
            Text(
                text = "you can see Complain and a feedback from a user",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp // Adjust font size as needed
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color(0x9FEF6C00)),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Add the image with transparency
                    Image(
                        painter = rememberImagePainter(R.drawable.img_banner1),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.3f  // Set transparency level (0.0f for fully transparent, 1.0f for fully opaque)
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Review all complaints regarding the cleaning service filed by active citizens.",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(16.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 15.dp, end = 15.dp),
                                onClick = {
                                    navController.navigate(Route.ComplainScreenAdmin)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "See Complain",
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color(0xFF0141E61)),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Add the image with transparency
                    Image(
                        painter = rememberImagePainter(R.drawable.img_banner2),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.3f  // Set transparency level (0.0f for fully transparent, 1.0f for fully opaque)
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Review all complaints regarding the Dustbin service filed by active citizens.",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(16.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 15.dp, end = 15.dp),
                                onClick = {
                                    navController.navigate(Route.ComplainScreen2Admin)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "See Complain",
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color(0xB9AD1457)),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Add the image with transparency
                    Image(
                        painter = rememberImagePainter(R.drawable.img_banner3),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.3f  // Set transparency level (0.0f for fully transparent, 1.0f for fully opaque)
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Review all complaints regarding the Pick-up service filed by active citizens.",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(16.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 15.dp, end = 15.dp),
                                onClick = {
                                    navController.navigate(Route.ComplainScreen3Admin)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "See Complain",
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color(0x9E2E7D32)),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Add the image with transparency
                    Image(
                        painter = rememberImagePainter(R.drawable.img),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.3f  // Set transparency level (0.0f for fully transparent, 1.0f for fully opaque)
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Review all Feedback regarding improvement by active citizens.",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(16.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 15.dp, end = 15.dp),
                                onClick = {
                                    navController.navigate(Route.FeedbackScreenAdmin)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "See Feedback",
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}