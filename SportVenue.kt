package com.example.booksport.model

data class SportVenue(
    val name: String,
    val location: String,
    val sportsAvailable: List<String>,
    val fields: List<String>,
    val schedules: List<String>
)
