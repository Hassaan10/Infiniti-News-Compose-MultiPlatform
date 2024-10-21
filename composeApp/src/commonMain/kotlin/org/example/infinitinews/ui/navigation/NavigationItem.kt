package org.example.infinitinews.ui.navigation

sealed class NavigationItem(var route: String) {
    data object Main : NavigationItem("MainScreen")
    data object Detail : NavigationItem("DetailScreen")
}