package com.example.swiftrescue1

data class ServiceCompletion(
    val id: Int,
    val mechanicName: String,
    val serviceDescription: String,
    val cost: Double,
    val timeTaken: String,
    val rating: Float,
    val review: String
)