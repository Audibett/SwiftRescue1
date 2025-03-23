package com.example.swiftrescue1


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DriverHomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_home)

        // Initialize MapView
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Buttons and navigation
        findViewById<Button>(R.id.btn_request_service).setOnClickListener {
            startActivity(Intent(this, ServiceRequestActivity::class.java))
        }

        findViewById<Button>(R.id.btn_my_requests).setOnClickListener {
            startActivity(Intent(this, MyRequestsActivity::class.java))
        }

        findViewById<Button>(R.id.btn_profile_settings).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<Button>(R.id.btn_chat).setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        findViewById<Button>(R.id.btn_complaint).setOnClickListener {
            startActivity(Intent(this, ComplaintActivity::class.java))
        }

        findViewById<Button>(R.id.btn_sos).setOnClickListener {
            sendSOSAlert()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val currentLocation = LatLng(-1.286389, 36.817223) // Default: Nairobi
        googleMap.addMarker(MarkerOptions().position(currentLocation).title("You are here"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
    }

    private fun sendSOSAlert() {
        // Logic for SOS alert (e.g., send notification, call emergency services)
    }

    // MapView lifecycle methods
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}