package com.example.swiftrescue1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ComplaintActivity : AppCompatActivity() {

    private lateinit var spinnerComplaintType: Spinner
    private lateinit var etComplaintDetails: EditText
    private lateinit var btnSubmitComplaint: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint)

        val complaintTypes = resources.getStringArray(R.array.complaint_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, complaintTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerComplaintType.adapter = adapter

        spinnerComplaintType = findViewById(R.id.spinnerComplaintType)
        etComplaintDetails = findViewById(R.id.etComplaintDetails)
        btnSubmitComplaint = findViewById(R.id.btnSubmitComplaint)

        btnSubmitComplaint.setOnClickListener {
            val complaintType = spinnerComplaintType.selectedItem.toString()
            val complaintDetails = etComplaintDetails.text.toString()

            if (complaintDetails.isEmpty()) {
                Toast.makeText(this, "Please enter complaint details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Save the complaint to the database or send it to an API
            Toast.makeText(this, "Complaint submitted: $complaintType", Toast.LENGTH_SHORT).show()
        }
    }
}