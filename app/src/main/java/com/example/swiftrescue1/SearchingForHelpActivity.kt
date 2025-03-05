package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SearchingForHelpActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var btnCancelSearch: Button
    private lateinit var tvSearching: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searching_for_help)

        mapView = findViewById(R.id.mapView)
        btnCancelSearch = findViewById(R.id.btnCancelSearch)
        tvSearching = findViewById(R.id.tvSearching)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        btnCancelSearch.setOnClickListener {
            finish() // Close this screen
        }

        // Simulating search process
        simulateSearch()
    }

    private fun simulateSearch() {
        tvSearching.text = "Searching for available mechanics..."
        // You can implement logic to search for mechanics using Firebase or API calls
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val currentLocation = LatLng(-1.286389, 36.817223) // Example: Nairobi, Kenya
        googleMap.addMarker(MarkerOptions().position(currentLocation).title("Your Location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
    }

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

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}