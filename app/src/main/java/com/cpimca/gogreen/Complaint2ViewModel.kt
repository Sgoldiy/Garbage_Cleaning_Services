package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class Complaint2ViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _complaints = MutableLiveData<List<Complaint2>>() // Fix the type here
    val complaints: LiveData<List<Complaint2>> = _complaints
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch complaints data
    fun fetchComplaints() {
        _isFetching.postValue(true)
        // Implement fetching complaints from Firestore or any other data source
        // For example:
        firestore.collection("complaintsOfDustbinService")
            .get()
            .addOnSuccessListener { documents ->
                val complaintsList = mutableListOf<Complaint2>()
                for (document in documents) {
                    val complaintText = document.getString("complaint") ?: ""
                    val userEmail = document.getString("userEmail") ?: ""

                    val complaint = Complaint2(
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

data class Complaint2(
    val complaint: String,
    val userEmail: String,
)

