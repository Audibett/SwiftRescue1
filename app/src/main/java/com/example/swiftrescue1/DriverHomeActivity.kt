package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DriverHomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var btnRequestHelp: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_home)

        // Initialize Floating Action Button
        btnRequestHelp = findViewById(R.id.btnRequestHelp)
        btnRequestHelp.setOnClickListener {
            startActivity(Intent(this, ServiceRequestActivity::class.java))
        }

        // Initialize Google Maps
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set Default Location (Example: Nairobi, Kenya)
        val nairobi = LatLng(-1.286389, 36.817223)
        googleMap.addMarker(MarkerOptions().position(nairobi).title("You are here"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nairobi, 15f))
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}