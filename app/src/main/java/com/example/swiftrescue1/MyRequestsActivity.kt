package com.example.swiftrescue1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftrescue1.adapters.RequestAdapter
import com.example.swiftrescue1.models.Request

class MyRequestsActivity : AppCompatActivity() {

    private lateinit var recyclerRequests: RecyclerView
    private lateinit var requestAdapter: RequestAdapter
    private val requestList = mutableListOf<Request>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_requests)

        recyclerRequests = findViewById(R.id.recyclerRequests)
        recyclerRequests.layoutManager = LinearLayoutManager(this)

        // Sample Data
        requestList.add(Request("1", "Towing Service", "Completed", "March 1, 2025"))
        requestList.add(Request("2", "Battery Jumpstart", "Pending", "March 2, 2025"))
        requestList.add(Request("3", "Flat Tire Change", "Canceled", "March 3, 2025"))

        requestAdapter = RequestAdapter(requestList)
        recyclerRequests.adapter = requestAdapter
    }
}