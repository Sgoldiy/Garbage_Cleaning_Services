package com.cpimca.gogreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CleaningService(navController: NavController) {

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
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
                                navController.navigate(Screen.Home.route)
                            }
                    )
                    Text(
                        text = "Clean your area through just one click",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp // Adjust font size as needed
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Text(
                text = "Make a request for cleaning Service",
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
                        .clickable { navController.navigate(Route.CleaningServiceUser) },
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
                            text = "Request For Cleaning", style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp // Adjust font size as needed
                            )
                        )
                    }
                }
            }
            Text(
                text = "You can see your request confirm by us",
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
                        .clickable { navController.navigate(Route.ConfirmServiceUser) },
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
                            text = "Confirm Service", style = TextStyle(
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