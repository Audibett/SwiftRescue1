package com.example.swiftrescue1

import android.graphics.Bitmap
import android.media.Image

data class ServiceRequests(
    val id: Int,
    val issue: String,
    val customerName: String,
    val location: String,
)