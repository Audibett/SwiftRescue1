package com.example.swiftrescue1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ServiceRequestActivity : AppCompatActivity() {
    private lateinit var etVehicleType: EditText
    private lateinit var etProblemDescription: EditText
    private lateinit var btnCaptureImage: Button
    private lateinit var btnSubmitRequest: Button
    private lateinit var ivCapturedImage: ImageView
    private var capturedImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_request)

        etVehicleType = findViewById(R.id.etVehicleType)
        etProblemDescription = findViewById(R.id.etProblemDescription)
        btnCaptureImage = findViewById(R.id.btnCaptureImage)
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest)
        ivCapturedImage = findViewById(R.id.ivCapturedImage)

        btnCaptureImage.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 101)
        }

        btnSubmitRequest.setOnClickListener {
            val vehicleType = etVehicleType.text.toString().trim()
            val problemDescription = etProblemDescription.text.toString().trim()

            if (vehicleType.isEmpty() || problemDescription.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (capturedImage == null) {
                Toast.makeText(this, "Please capture an image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Send data to backend (Firebase or custom database)
            Toast.makeText(this, "Request Submitted Successfully!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            capturedImage = data?.extras?.get("data") as Bitmap
            ivCapturedImage.setImageBitmap(capturedImage)
            ivCapturedImage.visibility = ImageView.VISIBLE
        }
    }
}

