package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ComplaintViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _complaints = MutableLiveData<List<Complaint>>() // Fix the type here
    val complaints: LiveData<List<Complaint>> = _complaints
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch complaints data
    fun fetchComplaints() {
        _isFetching.postValue(true)
        // Implement fetching complaints from Firestore or any other data source
        // For example:
        firestore.collection("complaintsOfCleaningService")
            .get()
            .addOnSuccessListener { documents ->
                val complaintsList = mutableListOf<Complaint>()
                for (document in documents) {
                    val complaintText = document.getString("complaint") ?: ""
                    val userEmail = document.getString("userEmail") ?: ""

                    val complaint = Complaint(
                        complaintText, userEmail
                    )
                    complaintsList.add(complaint)
                }
                _complaints.postValue(complaintsList)
                _isFetching.postValue(false)
            }
            .addOnFailureListener { exception ->
                // Handle error
                // Set isFetching to false in case of failure
                _isFetching.postValue(false)
            }
    }
}

data class Complaint(
    val complaint: String,
    val userEmail: String,
)

