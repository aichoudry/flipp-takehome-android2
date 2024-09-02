package com.flipp.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flipp.assignment.feature.search.SearchRoute
import com.flipp.assignment.navigation.Screen
import com.flipp.assignment.ui.theme.TakehomeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TakehomeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { pv ->

          val navController = rememberNavController()

          NavHost(navController = navController, startDestination = "search") {
            composable(Screen.Search.route) {
              SearchRoute(navController = navController)
            }
            composable(Screen.Other.route) {
              Text("other screen")
            }
          }

        }
      }
    }
  }
}