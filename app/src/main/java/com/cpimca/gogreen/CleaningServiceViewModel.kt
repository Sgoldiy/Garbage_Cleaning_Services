package com.cpimca.gogreen

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class CleaningServiceViewModel : ViewModel() {
    private val _requests = MutableLiveData<List<CleaningServiceRequest>>()
    val requests: LiveData<List<CleaningServiceRequest>> = _requests

    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching


    private val firestore = Firebase.firestore
    private val auth = Firebase.auth
    private val storage = Firebase.storage

    fun fetchCleaningServiceRequests() {
        _isFetching.value = true
        GlobalScope.launch {
            try {
                val fetchedRequests = CleaningServiceRepository().getCleaningServiceRequests()
                _requests.postValue(fetchedRequests)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isFetching.postValue(false)
            }
        }
    }

    fun storeRequest(
        imageUri: Uri?,
        address: String,
    ) {
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val email = currentUser?.email
        val imageUriString = imageUri?.toString() ?: ""

        // Fetch user's name and phone number from Firestore
        firestore.collection("users").document(userId!!).get()
            .addOnSuccessListener { documentSnapshot ->
                val userName = documentSnapshot.getString("name")
                val phoneNumber = documentSnapshot.getString("phone")

                // Construct request object
                val request = hashMapOf(
                    "userId" to userId,
                    "email" to email,
                    "userName" to userName,
                    "phoneNumber" to phoneNumber,
                    "imageUri" to imageUriString,
                    "address" to address,
                    "timestamp" to FieldValue.serverTimestamp()
                )

                // Add request to Firestore
                firestore.collection("requestForCleaningService").add(request)
                    .addOnSuccessListener { documentReference ->
                        // Successfully added document
                        val imageFileName =
                            "image_${System.currentTimeMillis()}_${UUID.randomUUID()}.jpg"
                        val imageRef = storage.reference.child("images").child(imageFileName)
                        val uploadTask = imageRef.putFile(imageUri!!)

                        uploadTask.addOnSuccessListener { _ ->
                            // Image uploaded successfully
                        }.addOnFailureListener { e ->
                            // Failed to upload image
                        }
                    }.addOnFailureListener { e ->
                        // Failed to add document
                    }
            }.addOnFailureListener { e ->
                // Failed to fetch user data
            }
    }

    fun removeFromCurrentCollection(request: CleaningServiceRequest) {
        val db = Firebase.firestore
        val currentRequestsRef = db.collection("requestForCleaningService")

        // Query the collection to find the document that matches the request
        currentRequestsRef.whereEqualTo("userId", request.userId)
            .whereEqualTo("email", request.email)
            .whereEqualTo("imageUri", request.imageUri)
            .whereEqualTo("userName",request.userName)
            .whereEqualTo("address", request.address)
            .whereEqualTo("timestamp", request.timestamp)
            .get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    // Delete the document
                    document.reference.delete().addOnSuccessListener {
                        // Document successfully deleted
                        Log.d(
                            "CleaningServiceViewModel",
                            "Document deleted from requestForCleaningService"
                        )
                    }.addOnFailureListener { e ->
                        // Handle failure
                        Log.e(
                            "CleaningServiceViewModel",
                            "Error deleting document from requestForCleaningService: ${e.message}",
                            e
                        )
                    }
                }
            }.addOnFailureListener { e ->
                // Handle failure
                Log.e(
                    "CleaningServiceViewModel",
                    "Error querying documents for deletion: ${e.message}",
                    e
                )
            }
    }
}

class CleaningServiceRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getCleaningServiceRequests(): List<CleaningServiceRequest> {
        // Fetch data from Firestore
        val snapshot = firestore.collection("requestForCleaningService").get().await()

        // Convert Firestore data to list of CleaningServiceRequest objects
        return snapshot.documents.map { document ->
            val userId = document.getString("userId")
            val email = document.getString("email")
            val userName = document.getString("userName") // Retrieve userName
            val phoneNumber = document.getString("phoneNumber")
            val imageUri = document.getString("imageUri")
            val address = document.getString("address")
            val timestamp = document.getTimestamp("timestamp")
            val documentId = document.id
            CleaningServiceRequest(
                userId, email, userName, phoneNumber, imageUri, address, timestamp, documentId
            )
        }
    }
}

data class CleaningServiceRequest(
    val userId: String?,
    val email: String?,
    val userName: String?, // Add userName field
    val phoneNumber: String?,
    val imageUri: String?,
    val address: String?,
    val timestamp: com.google.firebase.Timestamp?,
    val documentId: String,
    var isConfirmed: Boolean = false, // Add isConfirmed property
)