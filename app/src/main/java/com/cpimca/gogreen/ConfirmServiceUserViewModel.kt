package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConfirmServiceUserViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _confirmRequests = MutableLiveData<List<ConfirmRequest>>()
    val confirmRequests: LiveData<List<ConfirmRequest>> = _confirmRequests
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch confirmed requests data for the current user
    fun fetchConfirmRequestsForCurrentUser() {
        _isFetching.postValue(true)
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email

        // Check if the current user is authenticated
        if (currentUserEmail != null) {
            firestore.collection("confirm_requests")
                .whereEqualTo("email", currentUserEmail)
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
        } else {
            // If user is not authenticated, set isFetching to false
            _isFetching.postValue(false)
        }
    }
}
