package com.example.booksport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booksport.screens.BookingFormScreen
import com.example.booksport.screens.ConfirmationScreen
import com.example.booksport.screens.HomeScreen
import com.example.booksport.ui.theme.BookSportTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSportTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        // Home Screen
        composable("home") {
            HomeScreen(navController)
        }

        // Booking Screen
        composable(
            route = "booking/{venueName}",
            arguments = listOf(
                navArgument("venueName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val venueName = backStackEntry.arguments?.getString("venueName") ?: ""
            BookingFormScreen(navController, venueName)
        }

        // Confirmation Screen
        composable(
            route = "confirmation/{customerName}/{venueName}/{date}/{time}/{sport}/{field}",
            arguments = listOf(
                navArgument("customerName") { type = NavType.StringType },
                navArgument("venueName") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("time") { type = NavType.StringType },
                navArgument("sport") { type = NavType.StringType },
                navArgument("field") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ConfirmationScreen(
                navController = navController,
                customerName = backStackEntry.arguments?.getString("customerName") ?: "",
                venueName = backStackEntry.arguments?.getString("venueName") ?: "",
                date = backStackEntry.arguments?.getString("date") ?: "",
                time = backStackEntry.arguments?.getString("time") ?: "",
                sport = backStackEntry.arguments?.getString("sport") ?: "",
                field = backStackEntry.arguments?.getString("field") ?: ""
            )
        }
    }
}
