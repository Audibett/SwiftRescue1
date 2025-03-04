package com.example.swiftrescue1

data class Message(
    val text: String,
    val isSentByUser: Boolean // True = user message, False = received message
)