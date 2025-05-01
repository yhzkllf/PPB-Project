package com.example.booksport.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksport.model.SportVenue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val venues = listOf(
        SportVenue("Arena Futsal", "Surabaya", listOf("Futsal"), listOf("Lapangan 1", "Lapangan 2"), listOf ("08.00-10.00", "10.00-12.00", "12.00-14.00", "14.00-16.00", "16.00-18.00", "18.00-20.00") ),
        SportVenue("Badminton Center", "Surabaya",  listOf("Badminton"),  listOf("Court A", "Court B"), listOf ("09.00-11.00", "11.00-13.00", "13.00-15.00", "15.00-17.00", "17.00-19.00", "19.00-21.00")),
        SportVenue("Hoop Court", "Surabaya", listOf("Basket"), listOf("Arena A", "Arena B"), listOf ("10.00-12.00", "12.00-14.00", "14.00-16.00", "16.00-18.00", "18.00-20.00", "20.00-22.00"))
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BookSport - Tempat Olahraga") })
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            venues.forEach { venue ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("booking/${venue.name}")
                        }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(venue.name, style = MaterialTheme.typography.titleMedium)
                        Text("Lokasi: ${venue.location}")
                        Text("Olahraga: ${venue.sportsAvailable.joinToString()}")
                    }
                }
            }
        }
    }
}
