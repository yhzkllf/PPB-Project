package com.example.booksport.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConfirmationScreen(
    navController: NavController,
    customerName: String,
    venueName: String,
    date: String,
    time: String,
    sport: String,
    field: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Konfirmasi Pemesanan",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Terima kasih", style = MaterialTheme.typography.headlineSmall)
        Text("Pemesanan Anda telah dikonfirmasi.")

        Text("👤 Nama Pemesan: $customerName", style = MaterialTheme.typography.bodyLarge)
        Text("📍 Tempat: $venueName", style = MaterialTheme.typography.bodyLarge)
        Text("🏟️ Lapangan: $field", style = MaterialTheme.typography.bodyLarge)
        Text("📅 Tanggal: $date", style = MaterialTheme.typography.bodyLarge)
        Text("🕒 Waktu: $time", style = MaterialTheme.typography.bodyLarge)
        Text("⛹️ Jenis Olahraga: $sport", style = MaterialTheme.typography.bodyLarge)

        Button(
            onClick = {
                // Kembali ke form pemesanan
                navController.navigate("booking/$venueName")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pesan Lagi")
        }

        OutlinedButton(
            onClick = {
                // Kembali ke HomeScreen
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true } // clear backstack
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali ke Menu Utama")
        }
    }
}
