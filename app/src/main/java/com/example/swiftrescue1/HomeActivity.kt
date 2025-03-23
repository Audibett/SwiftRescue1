package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Find views by ID
        val btnDriver = findViewById<Button>(R.id.btn_driver)
        val btnMechanic = findViewById<Button>(R.id.btn_mechanic)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)

        // Navigate to Driver Home Screen
        btnDriver.setOnClickListener {
            val intent = Intent(this, DriverHomeActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Mechanic Home Screen
        btnMechanic.setOnClickListener {
            val intent = Intent(this, MechanicHomeActivity::class.java)
            startActivity(intent)
        }

        // Navigate back to Onboarding Screen
        btnBack.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}