package com.example.booksport.repository

import com.example.booksport.model.SportVenue

object SportVenueRepository {
    val venueList = listOf(
        SportVenue(
            name = "Arena Futsal",
            location = "Surabaya",
            sportsAvailable = listOf("Futsal"),
            fields = listOf("Lapangan 1", "Lapangan 2"),
            schedules = listOf("08.00-10.00", "10.00-12.00", "12.00-14.00", "14.00-16.00", "16.00-18.00", "18.00-20.00)")
        ),
        SportVenue(
            name = "Badminton Center",
            location = "Surabaya",
            sportsAvailable = listOf("Badminton"),
            fields = listOf("Court A", "Court B"),
            schedules = listOf("09.00-11.00", "11.00-13.00", "13.00-15.00", "15.00-17.00", "17.00-19.00", "19.00-21.00")
        ),
        SportVenue(
            name = "Hoop Court",
            location = "Surabaya",
            sportsAvailable = listOf("Basket"),
            fields = listOf("Arena A", "Arena B"),
            schedules = listOf("10.00-12.00", "12.00-14.00", "14.00-16.00", "16.00-18.00", "18.00-20.00", "20.00-22.00")
        )
    )

    fun getVenueByName(name: String): SportVenue? {
        return venueList.find { it.name == name }
    }
}
