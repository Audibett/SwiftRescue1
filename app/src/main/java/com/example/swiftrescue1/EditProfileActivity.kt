package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var imgEditProfile: ImageView
    private lateinit var btnChangeProfilePic: Button
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSaveChanges: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        imgEditProfile = findViewById(R.id.imgEditProfile)
        btnChangeProfilePic = findViewById(R.id.btnChangeProfilePic)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        btnSaveChanges = findViewById(R.id.btnSaveChanges)
        btnCancel = findViewById(R.id.btnCancel)

        // Load existing data (Replace with actual data retrieval)
        etName.setText("Audi Bett")
        etPhone.setText("+254 700 123 456")
        etEmail.setText("Aidibett@outlook.com")

        btnChangeProfilePic.setOnClickListener {
            Toast.makeText(this, "Profile picture change feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        btnSaveChanges.setOnClickListener {
            val newName = etName.text.toString()
            val newPhone = etPhone.text.toString()
            val newEmail = etEmail.text.toString()

            if (newName.isEmpty() || newPhone.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Save the updated profile data to the database

            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}