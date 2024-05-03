package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConfirmDustbinServiceUserViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _ConfirmDustbinRequests = MutableLiveData<List<ConfirmDustbinRequest>>()
    val ConfirmDustbinRequests: LiveData<List<ConfirmDustbinRequest>> = _ConfirmDustbinRequests
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch confirmed requests data for the current user
    fun fetchConfirmDustbinRequestsForCurrentUser() {
        _isFetching.postValue(true)
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email

        // Check if the current user is authenticated
        if (currentUserEmail != null) {
            firestore.collection("confirm_Dustbin")
                .whereEqualTo("email", currentUserEmail)
                .get()
                .addOnSuccessListener { documents ->
                    val ConfirmDustbinRequestsList = mutableListOf<ConfirmDustbinRequest>()
                    for (document in documents) {
                        val userName = document.getString("userName") ?: ""
                        val phoneNumber = document.getString("phoneNumber") ?: ""
                        val email = document.getString("email") ?: ""
                        val address = document.getString("address") ?: ""
                        val selectedNumber = document.getString("selectedNumber") ?: ""
                        val selectedTime = document.getString("selectedTime") ?: ""
                        // Create a ConfirmDustbinRequest object
                        val ConfirmDustbinRequest = ConfirmDustbinRequest(
                            userName,
                            phoneNumber,
                            email,
                            address,
                            selectedNumber,
                            selectedTime
                        )
                        ConfirmDustbinRequestsList.add(ConfirmDustbinRequest)
                    }
                    _ConfirmDustbinRequests.postValue(ConfirmDustbinRequestsList)
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