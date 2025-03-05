package com.example.swiftrescue1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftrescue1.R
import com.example.swiftrescue1.models.Request

class RequestAdapter(private val requestList: List<Request>) :
    RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvServiceType: TextView = view.findViewById(R.id.tvServiceType)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_request, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requestList[position]
        holder.tvServiceType.text = request.serviceType
        holder.tvStatus.text = request.status
        holder.tvDate.text = request.date
    }

    override fun getItemCount() = requestList.size
}