package com.example.swiftrescue1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftrescue1.adapters.RequestAdapter
import com.example.swiftrescue1.Request

class MyRequestsActivity : AppCompatActivity() {

    private lateinit var recyclerRequests: RecyclerView
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var dbHelper: DatabaseHelper
    private val requestList = mutableListOf<Request>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_requests)

        recyclerRequests = findViewById(R.id.recyclerRequests)
        recyclerRequests.layoutManager = LinearLayoutManager(this)

        dbHelper = DatabaseHelper(this)

        // Fetch requests from the database and update the list
        val requestsFromDb = dbHelper.getAllServiceCompletions()
        if (requestsFromDb.isNotEmpty()) {
            requestList.addAll(requestList)
        }

        requestAdapter = RequestAdapter(requestList)
        recyclerRequests.adapter = requestAdapter
    }
}