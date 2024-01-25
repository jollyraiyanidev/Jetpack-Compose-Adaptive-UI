package com.app.adaptivelayoutjetpack.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.adaptivelayoutjetpack.R
import com.app.adaptivelayoutjetpack.nav.NavSealedItem
import com.app.adaptivelayoutjetpack.nav.NavType
import com.app.adaptivelayoutjetpack.ui.theme.md_theme_light_outlineVariant
import com.app.adaptivelayoutjetpack.ui.theme.md_theme_light_primary


/**
 * Composable function that represents the left navigation drawer based on the provided navigation type.
 *
 * @param navigationType The type of navigation to be displayed in the drawer.
 * @param navController The navigation controller used for navigating between screens.
 */
@Composable
fun AdaptiveUiApp(
    navigationType: NavType,
    navController: NavHostController
) {
    // Render the appropriate type of navigation drawer based on the navigation type
    if (navigationType == NavType.PERMANENT_NAVIGATION_DRAWER) {
        // Render a permanent navigation drawer
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet {
                    LeftNavigationDrawerContent(navController = navController)
                }
            }
        ) {
            MainScreen(navController = navController, navigationType = navigationType)
        }
    } else {
        MainScreen(navController = navController, navigationType = navigationType)
    }
}

/**
 * Composable function that represents the content of the left navigation drawer.
 * Drawer for expanded screens
 *
 * @param navController The navigation controller used for navigating between screens.
 * @param modifier The modifier for the layout of the navigation drawer content.
 */
@Composable
fun LeftNavigationDrawerContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Define the list of navigation items in the drawer
    val navSealedItems =
        listOf(NavSealedItem.Home, NavSealedItem.Search, NavSealedItem.List, NavSealedItem.Profile)
    // Keep track of the selected item in the navigation drawer
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(color = md_theme_light_outlineVariant)
            .padding(all = 24.dp)
    ) {


        // Render the navigation items in the drawer
        navSealedItems.forEachIndexed { index, item ->
            NavigationDrawerItem(
                selected = selectedItem == index,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
                onClick = {
                    selectedItem = index
                    navController.navigate(item.path) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}