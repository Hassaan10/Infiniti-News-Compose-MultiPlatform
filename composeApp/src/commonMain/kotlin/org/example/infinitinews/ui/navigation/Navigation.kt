package org.example.infinitinews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.thauvin.erik.urlencoder.UrlEncoderUtil
import org.example.infinitinews.ui.screens.DetailsScreen
import org.example.infinitinews.ui.screens.MainScreen

@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.Main.route ) {
        composable(NavigationItem.Main.route) {
            MainScreen( onItemClick = { navController.navigate(
                route = "${NavigationItem.Detail.route}/${it.title}/${it.content}/${it.description}/${UrlEncoderUtil.encode(it.urlToImage!!)}"
            ) })
        }

        composable("${NavigationItem.Detail.route}/{title}/{content}/{description}/{urlToImage}", arguments = listOf(
            navArgument("title") { type = NavType.StringType },
            navArgument("content") { type = NavType.StringType },
            navArgument("description") { type = NavType.StringType },
            navArgument("urlToImage") { type = NavType.StringType },

            )) { backStackEntry ->

            val title = backStackEntry.arguments?.getString("title")
            val description = backStackEntry.arguments?.getString("description")
            val content = backStackEntry.arguments?.getString("content")
            val image = backStackEntry.arguments?.getString("urlToImage")

            DetailsScreen(image, title, description, content, navigateToBack = { navController.navigateUp() })
        }

    }

}