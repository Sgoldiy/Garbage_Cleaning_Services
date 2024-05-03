package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ConfirmServiceViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _confirmRequests = MutableLiveData<List<ConfirmRequest>>() // Fix the type here
    val confirmRequests: LiveData<List<ConfirmRequest>> = _confirmRequests
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch confirmed requests data
    fun fetchConfirmRequests() {
        _isFetching.postValue(true)
        // Implement fetching confirmed requests from Firestore or any other data source
        // For example:
        firestore.collection("confirm_requests")
            .get()
            .addOnSuccessListener { documents ->
                val confirmRequestsList = mutableListOf<ConfirmRequest>()
                for (document in documents) {
                    val userName = document.getString("userName") ?: ""
                    val phoneNumber = document.getString("phoneNumber") ?: ""
                    val email = document.getString("email") ?: ""
                    val address = document.getString("address") ?: ""

                    // Create a ConfirmRequest object
                    val confirmRequest = ConfirmRequest(userName, phoneNumber, email, address)
                    confirmRequestsList.add(confirmRequest)
                }
                _confirmRequests.postValue(confirmRequestsList)
                _isFetching.postValue(false)
            }
            .addOnFailureListener { exception ->
                // Handle error
                // Set isFetching to false in case of failure
                _isFetching.postValue(false)
            }
    }
}

data class ConfirmRequest(
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val address: String
)
