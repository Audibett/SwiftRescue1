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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_completion)

        // Initialize Views
        val tvServiceDetails = findViewById<TextView>(R.id.tvServiceDetails)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val etReview = findViewById<EditText>(R.id.etReview)
        val btnConfirmCompletion = findViewById<Button>(R.id.btnConfirmCompletion)
        val btnGoHome = findViewById<Button>(R.id.btnGoHome)

        // Set service details (you can fetch this from intent or database)
        tvServiceDetails.text = "Service: Tire Change\nMechanic: John Doe\nCost: $50\nTime: 30 mins"

        // Handle confirmation
        btnConfirmCompletion.setOnClickListener {
            val rating = ratingBar.rating
            val review = etReview.text.toString()

            if (rating == 0f) {
                Toast.makeText(this, "Please provide a rating!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save rating & review (this should be stored in the database)
            Toast.makeText(this, "Service Completed. Thank you!", Toast.LENGTH_SHORT).show()

            // Navigate to Home (or close activity)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Handle Go Home button
        btnGoHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}