package com.example.swiftrescue1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ServiceRequest(val issue: String, val customerName: String, val location: String)

class ServiceRequestAdapter(private val requests: List<ServiceRequest>) :
    RecyclerView.Adapter<ServiceRequestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val issue: TextView = view.findViewById(R.id.txtIssue)
        val customerName: TextView = view.findViewById(R.id.txtCustomerName)
        val location: TextView = view.findViewById(R.id.txtLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = requests[position]
        holder.issue.text = request.issue
        holder.customerName.text = request.customerName
        holder.location.text = request.location
    }

    override fun getItemCount(): Int = requests.size
}