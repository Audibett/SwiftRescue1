package com.example.swiftrescue1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MechanicHomeActivity : AppCompatActivity() {

    private lateinit var recyclerRequests: RecyclerView
    private lateinit var adapter: ServiceRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechanic_home)

        recyclerRequests = findViewById(R.id.recyclerRequests)
        recyclerRequests.layoutManager = LinearLayoutManager(this)

        // Example dummy data
        val requests = listOf(
            ServiceRequest("Flat Tire", "John Doe", "Nairobi CBD"),
            ServiceRequest("Engine Overheat", "Jane Doe", "Westlands"),
        )

        adapter = ServiceRequestAdapter(requests)
        recyclerRequests.adapter = adapter
    }
}