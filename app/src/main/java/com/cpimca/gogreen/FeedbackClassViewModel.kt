package com.cpimca.gogreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FeedbackClassViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _Feedbacks = MutableLiveData<List<Feedback>>() // Fix the type here
    val feedbacks: LiveData<List<Feedback>> = _Feedbacks
    private val _isFetching = MutableLiveData<Boolean>()
    val isFetching: LiveData<Boolean> = _isFetching

    // Function to fetch Feedbacks data
        fun fetchFeedbacks() {
        _isFetching.postValue(true)
        // Implement fetching Feedbacks from Firestore or any other data source
        // For example:
        firestore.collection("feedbacks")
            .get()
            .addOnSuccessListener { documents ->
                val FeedbacksList = mutableListOf<Feedback>()
                for (document in documents) {
                    val feedback = document.getString("feedback") ?: ""
                    val userEmail = document.getString("userEmail") ?: ""

                    val Feedback = Feedback(
                        feedback, userEmail
                    )
                    FeedbacksList.add(Feedback)
                }
                _Feedbacks.postValue(FeedbacksList)
                _isFetching.postValue(false)
            }
            .addOnFailureListener { exception ->
                // Handle error
                // Set isFetching to false in case of failure
                _isFetching.postValue(false)
            }
    }
}

data class Feedback(
    val feedback: String,
    val userEmail: String,
)

