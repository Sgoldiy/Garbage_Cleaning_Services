package com.cpimca.gogreen

import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun CleaningServiceUser(navController: NavController) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
                isLoading = true // Indicate that image loading is in progress
                error = null
            }
        }
    )

    var locationAddressForCleaningService by remember { mutableStateOf(TextFieldValue("")) }

    val viewModel: CleaningServiceViewModel = viewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
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
                        .padding(start = 16.dp)
                        .clickable {
                            navController.navigate(Route.CleaningService)
                        }
                )
                Text(
                    text = "Make a request for cleaning Service",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp // Adjust font size as needed
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        imageUri?.let { }
        Image(
            modifier = Modifier
                .padding(top = 40.dp)
                .height(275.dp)
                .width(275.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    galleryLauncher.launch("image/*")
                },
            painter = getPainter(imageUri),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Add a photo of a area you want to clean from a gallery", style = TextStyle(
                color = Color(0xFF0141E61)
            )
        )
        val context = LocalContext.current

        var isFetching by remember { mutableStateOf(false) }

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
            Spacer(modifier = Modifier.height(20.dp))
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
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                viewModel.storeRequest(imageUri, locationAddressForCleaningService.text)
                navController.navigate(Route.CleaningService) {
                    popUpTo(Route.CleaningServiceUser) {
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


@Composable
fun getPainter(imageUri: Uri?): Painter {
    return if (imageUri != null) {
        rememberAsyncImagePainter(
            model = imageUri,
        )
    } else {
        // Placeholder painter resource
        painterResource(id = R.drawable.baseline_photo_size_select_actual_24)
    }
}