package com.cpimca.gogreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplainScreen(navController: NavController) {
    var selectedOptionIndex by remember { mutableStateOf(0) }
    val options = listOf("Cleaning Issue", "Late Service", "Worker Behavior Issue")
    var selectedOption by remember { mutableStateOf(options[selectedOptionIndex]) }

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
                        navController.popBackStack(Route.ComplainScreen, inclusive = true)
                    }
            )
            Text(
                text = "apologise for problem tell us we will fix it",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp // Adjust font size as needed
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp , start = 12.dp , end = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF0141E61)),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Please select the type of complaint:",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            var selectedOptionIndex by remember { mutableStateOf(0) }
            RadioGroup(
                options = options,
                onSelected = { index ->
                    selectedOptionIndex = index
                    selectedOption = options[index]
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                onClick = {
                    // Store complaint data in Firestore
                    storeComplaintData(selectedOption)
                    navController.popBackStack(Route.ComplainScreen, inclusive = true)
                },
            ) {
                Text(
                    text = "Submit",
                    color = Color(0xFF0141E61)
                )

            }
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RadioGroup(
        options: List<String>,
        onSelected: (Int) -> Unit,
    ) {
        var selectedOptionIndex by remember { mutableStateOf(0) }

        Column {
            options.forEachIndexed { index, text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        selectedOptionIndex = index
                        onSelected(index)
                    }
                ) {
                    RadioButton(
                        selected = index == selectedOptionIndex,
                        onClick = {
                            selectedOptionIndex = index
                            onSelected(index)
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Color.White, unselectedColor = Color.White),
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Text(
                        text = text,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }

fun storeComplaintData(complaint: String) {
    val currentUser = FirebaseAuth.getInstance().currentUser

    // Get a reference to the Firestore database
    val db = FirebaseFirestore.getInstance()
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val currentTime = dateFormat.format(calendar.time)

    // Create a new document with a generated ID
    val complaintData = hashMapOf(
        "complaint" to complaint,
        "timestamp" to currentTime, // Current timestamp
        "userId" to currentUser?.uid, // Current user ID
        "userEmail" to currentUser?.email // Current user email
    )

    // Add a new document with a generated ID
    db.collection("complaintsOfCleaningService")
        .add(complaintData)
        .addOnSuccessListener { documentReference ->
            // Document added successfully
            println("Complaint added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            // Error handling
            println("Error adding complaint: $e")
        }
}
