package com.cpimca.gogreen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ConfirmService(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .background(Color(0xFF0141E61))


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        navController.navigate(Route.DashBoard) {
                            popUpTo(Route.ConfirmService) {
                                inclusive = true
                            }
                        }
                    }
            )
            androidx.compose.material3.Text(
                text = "You can see Cleaning Requests Confirm by you",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp // Adjust font size as needed
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    val viewModel: ConfirmServiceViewModel = viewModel() // Initialize the view model

    // Fetch data when the composable is initially composed
    LaunchedEffect(Unit) {
        viewModel.fetchConfirmRequests()
    }


    // Observe the list of confirm requests from the view model
    val confirmRequests by viewModel.confirmRequests.observeAsState(emptyList())

    val isFetching by viewModel.isFetching.observeAsState(false) // Observe fetching state

    if (isFetching) {
        // Show progress bar while fetching data
        CircularProgressIndicator(
            modifier = Modifier.padding(top = 60.dp)
        )
    } else {
        // Show confirm requests once data is fetched
        ConfirmRequestsList(confirmRequests = confirmRequests)
    }
}

@Composable
fun ConfirmRequestsList(confirmRequests: List<ConfirmRequest>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 60.dp)) {
        items(confirmRequests) { request ->
            ConfirmRequestCard(request = request)
        }
    }
}

@Composable
fun ConfirmRequestCard(request: ConfirmRequest) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(0.8.dp,color = Color(0xFF2962FF), shape = RoundedCornerShape(16.dp)),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Name
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Name icon",
                    tint = Color(0xFF2962FF),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Name: ${request.userName}")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Phone Number
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Phone icon",
                    tint = Color(0xFFE5204D19),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Phone Number: ${request.phoneNumber}")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Email
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email icon",
                    tint = Color(0xFFFF6F00),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Email: ${request.email}")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Address
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Address icon",
                    tint = Color.Red,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Address:\n${request.address}")
            }
            val sendMessage =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        // Message sent successfully
                    }
                }
            val context = LocalContext.current
            Row(verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.material3.Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("smsto:${request.phoneNumber}")
                            putExtra(
                                "sms_body",
                                "A cleaning service that you have request for is complete \nThank you keep bharat clean"
                            ) // You can pre-fill the message here
                        }
                        if (intent.resolveActivity(context.packageManager) != null) {
                            sendMessage.launch(intent)
                        }
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(
                            0xFF0141E61
                        )
                    ),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Message", color = Color.White)
                }
            }
        }
    }
}




