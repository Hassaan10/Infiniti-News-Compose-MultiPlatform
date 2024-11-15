package org.example.infinitinews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.thauvin.erik.urlencoder.UrlEncoderUtil
import org.example.infinitinews.data.CONTENT
import org.example.infinitinews.data.DESCRIPTION
import org.example.infinitinews.data.TITLE
import org.example.infinitinews.data.URL_TO_IMAGE
import org.example.infinitinews.ui.screens.DetailsScreen
import org.example.infinitinews.ui.screens.MainScreen

@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.Main.route ) {
        composable(NavigationItem.Main.route) {
            MainScreen( onItemClick = {
                try {
                    navController.navigate(
                        route = "${NavigationItem.Detail.route}/${it.title}/${it.content}/${it.description}/${UrlEncoderUtil.encode(it.urlToImage!!)}"
                    )
                }
                catch (e:IllegalStateException) {
                    e.printStackTrace()
                }
                 })
        }

        composable("${NavigationItem.Detail.route}/{${TITLE}}/{$CONTENT}/{$DESCRIPTION}/{$URL_TO_IMAGE}", arguments = listOf(
            navArgument(TITLE) { type = NavType.StringType },
            navArgument(CONTENT) { type = NavType.StringType },
            navArgument(DESCRIPTION) { type = NavType.StringType },
            navArgument(URL_TO_IMAGE) { type = NavType.StringType },

            )) { backStackEntry ->

            val title = backStackEntry.arguments?.getString(TITLE)
            val description = backStackEntry.arguments?.getString(DESCRIPTION)
            val content = backStackEntry.arguments?.getString(CONTENT)
            val image = backStackEntry.arguments?.getString(URL_TO_IMAGE)

            DetailsScreen(image, title, description, content, navigateToBack = { navController.navigateUp() })
        }

    }

}