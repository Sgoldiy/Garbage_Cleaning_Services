package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ConfirmDustbinServiceViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _ConfirmDustbinRequests = MutableLiveData<List<ConfirmDustbinRequest>>() // Fix the type here
    val ConfirmDustbinRequests: LiveData<List<ConfirmDustbinRequest>> = _ConfirmDustbinRequests
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch confirmed requests data
    fun fetchConfirmDustbinRequests() {
        _isFetching.postValue(true)
        // Implement fetching confirmed requests from Firestore or any other data source
        // For example:
        firestore.collection("confirm_Dustbin")
            .get()
            .addOnSuccessListener { documents ->
                val ConfirmDustbinRequestsList = mutableListOf<ConfirmDustbinRequest>()
                for (document in documents) {
                    val userName = document.getString("userName")?: ""
                    val phoneNumber = document.getString("phoneNumber")?: ""
                    val email = document.getString("email")?: ""
                    val address = document.getString("address") ?: ""
                    val selectedNumber = document.getString("selectedNumber")?: ""
                    val selectedModification = document.getString("selectedModification")?: ""
                    // Create a ConfirmDustbinRequest object
                    val ConfirmDustbinRequest = ConfirmDustbinRequest(userName, phoneNumber, email, address , selectedNumber , selectedModification)
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
    }
}

data class ConfirmDustbinRequest(
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val selectedNumber: String?,
    val selectedModification: String?,
)