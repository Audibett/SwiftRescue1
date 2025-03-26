package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ServiceCompletionActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_completion)

        // Initialize Database Helper
        dbHelper = DatabaseHelper(this)

        // Initialize Views
        val tvServiceDetails = findViewById<TextView>(R.id.tvServiceDetails)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val etReview = findViewById<EditText>(R.id.etReview)
        val btnConfirmCompletion = findViewById<Button>(R.id.btnConfirmCompletion)
        val btnGoHome = findViewById<Button>(R.id.btnGoHome)

        // Dummy data (this can be fetched from intent or database)
        val mechanicName = "Weddy wamaitha"
        val serviceDescription = "Tire Change"
        val cost = 50.0
        val timeTaken = "30 mins"

        // Set service details
        tvServiceDetails.text = "Service: $serviceDescription\nMechanic: $mechanicName\nCost: $$cost\nTime: $timeTaken"

        // Handle confirmation
        btnConfirmCompletion.setOnClickListener {
            val rating = ratingBar.rating
            val review = etReview.text.toString()

            if (rating == 0f) {
                Toast.makeText(this, "Please provide a rating!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save rating & review in database
            val success = dbHelper.addServiceCompletion(mechanicName, serviceDescription, cost, timeTaken, rating, review)
            if (success) {
                Toast.makeText(this, "Service Completed. Thank you!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed to save service completion!", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Go Home button
        btnGoHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}