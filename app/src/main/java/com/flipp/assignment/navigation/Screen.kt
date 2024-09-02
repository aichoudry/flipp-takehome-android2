package com.flipp.assignment.navigation

sealed class Screen(val route: String) {
  object Search : Screen("search")
  object Other : Screen("other")
}
