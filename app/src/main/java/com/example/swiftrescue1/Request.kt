package com.example.swiftrescue1

import android.graphics.Bitmap
import com.google.android.gms.common.api.Status
import java.util.Date

data class Request(
    val id: Int, // Default value for new records
    val vehicleType: String,
    val problemDescription: String,
    val image: Bitmap,
    val serviceType: CharSequence,
    val status: CharSequence,
    val date: CharSequence,

)