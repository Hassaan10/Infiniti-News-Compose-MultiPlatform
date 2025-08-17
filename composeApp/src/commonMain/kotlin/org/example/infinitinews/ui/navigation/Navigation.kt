package org.example.infinitinews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.example.infinitinews.data.model.Article
import org.example.infinitinews.ui.screens.DetailsScreen
import org.example.infinitinews.ui.screens.MainScreen
import kotlin.reflect.typeOf


@Serializable
object Main

// Define a profile destination that takes an ID
@Serializable
data class Detail(val article: Article)


@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main) {

        composable<Main> {
            MainScreen(onItemClick = { navController.navigate(Detail(it)) })
        }

        composable<Detail>(typeMap = mapOf(typeOf<Article>() to ArticleNavType)) { backStackEntry ->
            val parameters = backStackEntry.toRoute<Detail>()
            DetailsScreen(parameters.article, navigateToBack = { navController.navigateUp() })
        }

    }

}