package com.app.adaptivelayoutjetpack.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.adaptivelayoutjetpack.nav.NavSealedItem
import com.app.adaptivelayoutjetpack.ui.composables.tabs.HomeScreen
import com.app.adaptivelayoutjetpack.ui.composables.tabs.ListScreen
import com.app.adaptivelayoutjetpack.ui.composables.tabs.ProfileScreen
import com.app.adaptivelayoutjetpack.ui.composables.tabs.SearchScreen

/**
 * Composable function that defines the navigation screens and their corresponding destinations.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(navController, startDestination = NavSealedItem.Home.path) {
        composable(NavSealedItem.Home.path) { HomeScreen() }
        composable(NavSealedItem.Search.path) { SearchScreen() }
        composable(NavSealedItem.List.path) { ListScreen() }
        composable(NavSealedItem.Profile.path) { ProfileScreen() }
    }
}