package com.example.swiftrescue1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ServiceRequestActivity : AppCompatActivity() {
    private lateinit var etVehicleType: EditText
    private lateinit var etProblemDescription: EditText
    private lateinit var btnSubmitRequest: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_request)

        etVehicleType = findViewById(R.id.etVehicleType)
        etProblemDescription = findViewById(R.id.etProblemDescription)
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest)

        dbHelper = DatabaseHelper(this)

        btnSubmitRequest.setOnClickListener {
            val vehicleType = etVehicleType.text.toString().trim()
            val problemDescription = etProblemDescription.text.toString().trim()

            if (vehicleType.isEmpty() || problemDescription.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = dbHelper.addServiceRequest(vehicleType, problemDescription)
            if (success) {
                Toast.makeText(this, "Request Submitted Successfully!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to submit request", Toast.LENGTH_LONG).show()
            }
        }
    }
}