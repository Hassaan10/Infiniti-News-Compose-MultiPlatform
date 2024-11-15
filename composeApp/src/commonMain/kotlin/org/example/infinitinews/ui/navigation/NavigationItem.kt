package org.example.infinitinews.ui.navigation

import org.example.infinitinews.data.DETAIL_SCREEN
import org.example.infinitinews.data.MAIN_SCREEN

sealed class NavigationItem(var route: String) {
    data object Main : NavigationItem(MAIN_SCREEN)
    data object Detail : NavigationItem(DETAIL_SCREEN)
}