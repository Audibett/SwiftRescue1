package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar

class MechanicHomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ServiceRequestAdapter
    private val serviceRequests = listOf(
        ServiceRequest("Flat Tire", "John Doe", "Nairobi CBD"),
        ServiceRequest("Battery Jumpstart", "Jane Doe", "Westlands"),
        ServiceRequest("Towing Needed", "Mike Smith", "Kilimani")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechanic_home)

        // Back button to login
        findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // RecyclerView Setup
        recyclerView = findViewById(R.id.recycler_requests)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ServiceRequestAdapter(serviceRequests)
        recyclerView.adapter = adapter

        // Navigation Buttons
        findViewById<Button>(R.id.btn_new_requests).setOnClickListener {
            startActivity(Intent(this, ServiceRequestActivity::class.java))
        }
        findViewById<Button>(R.id.btn_accepted_jobs).setOnClickListener {
            startActivity(Intent(this, MyRequestsActivity::class.java))
        }
        findViewById<Button>(R.id.btn_profile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        findViewById<Button>(R.id.btn_chat).setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }
}
