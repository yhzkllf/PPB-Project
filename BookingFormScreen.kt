package com.example.booksport.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksport.repository.SportVenueRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingFormScreen(navController: NavController, venueName: String) {
    val venue = remember { SportVenueRepository.getVenueByName(venueName) }

    var customerName by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var selectedField by remember { mutableStateOf("") }
    val selectedSport = venue?.sportsAvailable?.firstOrNull().orEmpty()
    var showError by remember { mutableStateOf(false) }

    val timeOptions = venue?.schedules ?: listOf()
    val fieldOptions = venue?.fields ?: listOf()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Formulir Pemesanan") }) }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Tempat: $venueName", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = customerName,
                onValueChange = { customerName = it },
                label = { Text("Nama Pemesan") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = { Text("Tanggal (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // Dropdown untuk waktu
            var expandedTime by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = expandedTime, onExpandedChange = { expandedTime = !expandedTime }) {
                OutlinedTextField(
                    value = selectedTime,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Pilih Waktu") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTime) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expandedTime, onDismissRequest = { expandedTime = false }) {
                    timeOptions.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = {
                            selectedTime = it
                            expandedTime = false
                        })
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Dropdown untuk lapangan
            var expandedField by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = expandedField, onExpandedChange = { expandedField = !expandedField }) {
                OutlinedTextField(
                    value = selectedField,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Pilih Lapangan") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedField) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expandedField, onDismissRequest = { expandedField = false }) {
                    fieldOptions.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = {
                            selectedField = it
                            expandedField = false
                        })
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            if (showError) {
                Text("Semua field wajib diisi.", color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                if (
                    customerName.isNotBlank() &&
                    selectedDate.isNotBlank() &&
                    selectedTime.isNotBlank() &&
                    selectedField.isNotBlank()
                ) {
                    navController.navigate("confirmation/$customerName/$venueName/$selectedDate/$selectedTime/$selectedSport/$selectedField")
                } else {
                    showError = true
                }
            }) {
                Text("Pesan Sekarang")
            }
        }
    }
}
