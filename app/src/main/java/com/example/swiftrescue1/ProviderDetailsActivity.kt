package com.example.swiftrescue1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProviderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_details)

        val imgMechanic = findViewById<ImageView>(R.id.imgMechanic)
        val tvMechanicName = findViewById<TextView>(R.id.tvMechanicName)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val tvServiceType = findViewById<TextView>(R.id.tvServiceType)
        val tvETA = findViewById<TextView>(R.id.tvETA)
        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnChat = findViewById<Button>(R.id.btnChat)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // Dummy data (you'll replace this with real API data)
        tvMechanicName.text = "John Doe"
        ratingBar.rating = 4.5f
        tvServiceType.text = "Tire Change"
        tvETA.text = "ETA: 10 mins"

        // Call Button - Opens Phone Dialer
        btnCall.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            phoneIntent.data = Uri.parse("tel:+254700123456")
            startActivity(phoneIntent)
        }

        // Chat Button - Opens Chat Activity
        btnChat.setOnClickListener {
            val chatIntent = Intent(this, ChatActivity::class.java)
            chatIntent.putExtra("providerName", "John Doe")
            startActivity(chatIntent)
        }

        // Cancel Button - Goes Back to Home Screen
        btnCancel.setOnClickListener {
            finish()
        }
    }
}