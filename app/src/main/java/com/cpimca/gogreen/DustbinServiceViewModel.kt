package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DustbinServiceViewModel : ViewModel() {

    private val _requests = MutableLiveData<List<DustbinRequest>>()
    val requests: LiveData<List<DustbinRequest>> = _requests

    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    private val firestore = Firebase.firestore
    private val auth = Firebase.auth


    @OptIn(DelicateCoroutinesApi::class)
    fun fetchDustbinRequests() {
        _isFetching.value = true
        GlobalScope.launch {
            try {
                val fetchedRequests = DustbinRepository().getDustbinRequests()
                _requests.postValue(fetchedRequests)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isFetching.postValue(false)
            }
        }
    }

    fun storeRequest(
        address: String,
        selectedNumber: String,
        selectedModification: String,
    ) {
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val email = currentUser?.email

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
                    "address" to address,
                    "selectedNumber" to selectedNumber,
                    "selectedModification" to selectedModification,
                    "timestamp" to FieldValue.serverTimestamp()
                )

                // Add request to Firestore
                firestore.collection("requestForDustbin").add(request)
                    .addOnSuccessListener { documentReference ->
                        // Successfully added document

                    }.addOnFailureListener { e ->
                        // Failed to add document
                    }
            }.addOnFailureListener { e ->
                // Failed to fetch user data
            }
    }

    fun removeFromCurrentCollection(request: DustbinRequest) {
        val db = Firebase.firestore
        val currentRequestsRef = db.collection("requestForDustbin")

        // Query the collection to find the document that matches the request
        currentRequestsRef.whereEqualTo("userId", request.userId)
            .whereEqualTo("email", request.email)
            .whereEqualTo("userName", request.userName)
            .whereEqualTo("address", request.address)
            .whereEqualTo("selectedNumber", request.selectedNumber)
            .whereEqualTo("selectedModification", request.selectedModification)
            .whereEqualTo("timestamp", request.timestamp).get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    // Delete the document
                    document.reference.delete().addOnSuccessListener {
                        // Document successfully deleted
                    }.addOnFailureListener { e ->
                        // Handle failure
                    }
                }
            }.addOnFailureListener { e ->
                // Handle failure
            }
    }
}

class DustbinRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getDustbinRequests(): List<DustbinRequest> {
        // Fetch data from Firestore
        val snapshot = firestore.collection("requestForDustbin").get().await()

        // Convert Firestore data to list of DustbinRequest objects
        return snapshot.documents.map { document ->
            val userId = document.getString("userId")
            val email = document.getString("email")
            val userName = document.getString("userName") // Retrieve userName
            val phoneNumber = document.getString("phoneNumber")
            val address = document.getString("address")
            val selectedNumber = document.getString("selectedNumber")
            val selectedModification = document.getString("selectedModification")
            val timestamp = document.getTimestamp("timestamp")
            val documentId = document.id
            DustbinRequest(
                userId,
                email,
                userName,
                phoneNumber,
                address,
                selectedNumber,
                selectedModification,
                timestamp,
                documentId,
            )
        }
    }
}

data class DustbinRequest(
    val userId: String?,
    val email: String?,
    val userName: String?, // Add userName field
    val phoneNumber: String?,
    val address: String?,
    val selectedNumber: String?,
    val selectedModification: String?,
    val timestamp: com.google.firebase.Timestamp?,
    val documentId: String,
    var isConfirmed: Boolean = false, // Add isConfirmed property
)