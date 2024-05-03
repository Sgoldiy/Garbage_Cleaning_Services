package com.cpimca.gogreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestFregment(navController: NavController, onItemSelected: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp, bottom = 75.dp)
    ) {
        item {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color(0xFF0141E61))
//            ) {
//                Text(
//                    text = "How to use our Application and get Service",
//                    style = TextStyle(
//                        color = Color.White,
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
//            }
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 16.dp)
//                    .border(
//                        width = 0.5.dp,
//                        color = Color(0xFF0141E61),
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                colors = CardDefaults.cardColors(Color.White)
//            ) {
//                Text(
//                    text = "Step To Use Dustbin Service", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> Public area or hospital if they don't have a Dustbin", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> Use our Service and tell us How Many dustbin you hav require", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> For a public area we provide a simple dustbin with blue and green color", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> For a hospital area we provide a 5 dustbin pack with different usage", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> if we will Confirm then you will see at Confirm Service Screen", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//            }
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 16.dp)
//                    .border(
//                        width = 0.5.dp,
//                        color = Color(0xFF0141E61),
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                colors = CardDefaults.cardColors(Color.White)
//            ) {
//                Text(
//                    text = "Step To Use Cleaning Service", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> When you see a area with a garbage you will inform us", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> Upload a Photo of that area and mention location", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> if we will Confirm then you will see at Confirm Service Screen", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//            }
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 16.dp)
//                    .border(
//                        width = 0.5.dp,
//                        color = Color(0xFF0141E61),
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                colors = CardDefaults.cardColors(Color.White)
//            ) {
//                Text(
//                    text = "Step To Use everyday Pick-up Service", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> If you don't have any Pick-up truck for a garbage tell us", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> give us a number if your society has more then 250 house use 2 Pick-up truck", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> If we confirm then we will start out service after 7 days from your request", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(1.dp)
//                        .background(Color(0xFF0141E61))
//                        .fillMaxWidth()
//                )
//                Text(
//                    text = "-> if we will Confirm then you will see at Confirm Pick-up", // Customize text as needed
//                    style = TextStyle(
//                        color = Color(0xFF0141E61), // Set text color to white
//                        fontSize = 16.sp // Adjust font size as needed
//                    ),
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 8.dp)
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally)
//                )
//            }
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
                            text = "If you have any complaints with our cleaning service, let us know.",
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
                                    navController.navigate(Route.ComplainScreen)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "Complain",
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
                            text = "If you have any complaints with our Dustbin service, let us know.",
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
                                    navController.navigate(Route.ComplainScreen2)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "Complain",
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
                            text = "If you have any complaints with our Pick-up service, let us know.",
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
                                    navController.navigate(Route.ComplainScreen3)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "Complain",
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
                            text = "If you have any Feedback for improve our service, share the idea.",
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
                                    navController.navigate(Route.FeedbackScreen)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(
                                    text = "Feedback",
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