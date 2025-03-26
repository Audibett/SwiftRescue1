package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var btnResetPassword: Button
    private lateinit var btnBackToLogin: Button
    private lateinit var dbHelper: DatabaseHelper // Initialize DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        etEmail = findViewById(R.id.etEmail)
        btnResetPassword = findViewById(R.id.btnResetPassword)
        btnBackToLogin = findViewById(R.id.btnBackToLogin)
        dbHelper = DatabaseHelper(this) // Instantiate DatabaseHelper

        btnResetPassword.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if the user exists in the database
            if (dbHelper.userExists(email)) { // Corrected method name
                // TODO: Implement actual password reset logic (send email or allow password change)
                Toast.makeText(this, "Password reset instructions sent to $email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Email not registered", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}