package com.cpimca.gogreen

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun DustbinServiceUser(navController: NavController) {
    val viewModel: DustbinServiceViewModel = viewModel()

    var locationAddressForCleaningService by remember { mutableStateOf(TextFieldValue("")) }
    var isFetching by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var selectedNumber by remember { mutableStateOf("1") }
    var selectedModification by remember { mutableStateOf("") }
    var showDropdownMenu1 by remember { mutableStateOf(false) }
    var showDropdownMenu2 by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // First outlined text field with dropdown
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
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
                        .padding(start = 16.dp)
                        .clickable {
                            navController.navigate(Route.DustbinService)
                        }
                )
                Text(
                    text = "Make a request for dustbin Service",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp // Adjust font size as needed
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Box (
            modifier = Modifier
                .padding(top = 40.dp)
        ){
            OutlinedTextField(
                value = "Number of Dustbin: $selectedNumber",
                onValueChange = {},
                modifier = Modifier
                    .width(343.dp)
                    .height(52.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        // Toggle dropdown menu visibility
                        showDropdownMenu1 = !showDropdownMenu1
                    }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                },
                readOnly = true // Make the text field read-only
            )

            // Dropdown menu
            DropdownMenu(
                expanded = showDropdownMenu1,
                onDismissRequest = { showDropdownMenu1 = false },
                modifier = Modifier
                    .width(343.dp)
            ) {
                DropdownMenuItem(onClick = {
                    selectedNumber = 1.toString()
                    showDropdownMenu1 = false // Close the menu after selection
                }) {
                    Text(text = "1")
                }
                DropdownMenuItem(onClick = {
                    selectedNumber = 2.toString()
                    showDropdownMenu1 = false // Close the menu after selection
                }) {
                    Text(text = "2")
                }
                DropdownMenuItem(onClick = {
                    selectedNumber = 3.toString()
                    showDropdownMenu1 = false // Close the menu after selection
                }) {
                    Text(text = "3")
                }
                DropdownMenuItem(onClick = {
                    selectedNumber = 4.toString()
                    showDropdownMenu1 = false // Close the menu after selection
                }) {
                    Text(text = "4")
                }
                DropdownMenuItem(onClick = {
                    selectedNumber = 5.toString()
                    showDropdownMenu1 = false // Close the menu after selection
                }) {
                    Text(text = "5")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Second outlined text field with time picker
        Box {
            OutlinedTextField(
                value = "Select modification: $selectedModification",
                onValueChange = {},
                modifier = Modifier
                    .width(343.dp)
                    .height(52.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        // Toggle dropdown menu visibility
                        showDropdownMenu2 = !showDropdownMenu2
                    }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                },
                readOnly = true // Make the text field read-only
            )
            // Dropdown menu
            DropdownMenu(
                expanded = showDropdownMenu2,
                onDismissRequest = { showDropdownMenu2 = false },
                modifier = Modifier
                    .width(343.dp)
            ) {
                DropdownMenuItem(onClick = {
                    selectedModification = "Public use"
                    showDropdownMenu2 = false // Close the menu after selection
                }) {
                    Text(text = "Public use")
                }
                DropdownMenuItem(onClick = {
                    selectedModification = "Hospital use"
                    showDropdownMenu2 = false // Close the menu after selection
                }) {
                    Text(text = "Hospital use")
                }
            }
        }
        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted: Boolean ->
                    if (isGranted) {
                        isFetching = true // Indicate that location fetching is in progress
                        requestLocationUpdates(context) { location ->
                            getAddressFromLocation(context, location) { address ->
                                Log.d("Location", "Fetched address: $address")
                                locationAddressForCleaningService = TextFieldValue(address)
                                isFetching = false
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Location permission is required",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            )
        if (isFetching) {
            CircularProgressIndicator(
                color = Color(0xFF0141E61)
            )
        } else {
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                modifier = Modifier
                    .width(343.dp),
                value = locationAddressForCleaningService,
                onValueChange = {
                    locationAddressForCleaningService = it
                },
                placeholder = {
                    Text(
                        text = "Address of area",
                        style = TextStyle(
                            color = Color.Gray
                        )
                    )
                },
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        ClickableText(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp),
            text = AnnotatedString("Get Current Location"),
            style = TextStyle(
                color = Color(0xFF0141E61),
                textDecoration = TextDecoration.Underline
            ),
            onClick = {
                if (hasLocationPermission(context)) {
                    isFetching = true
                    requestLocationUpdates(context) { location ->
                        getAddressFromLocation(context, location) { address ->
                            Log.d("Location", "Fetched address: $address")
                            locationAddressForCleaningService = TextFieldValue(address)
                            isFetching = false
                        }
                    }
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                viewModel.storeRequest(
                    locationAddressForCleaningService.text,
                    selectedNumber,
                    selectedModification
                )
                navController.navigate(Route.DustbinService) {
                    popUpTo(Route.DustbinServiceUser) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .width(343.dp)
                .height(52.dp)
                .border(1.dp, Color(0xFF0141E61), shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0141E61)),
        ) {
            Text(
                text = "REQUEST", style = TextStyle(
                    color = Color.White,
                    fontSize = 13.sp,
                )
            )
        }
    }
}