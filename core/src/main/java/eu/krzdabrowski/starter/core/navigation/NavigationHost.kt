package eu.krzdabrowski.starter.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph

@Composable
fun NavigationHost(
    factories: Set<NavigationFactory>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    val navGraph = navController.createGraph(
        startDestination = NavigationDestination.Rockets.route
    ) {
        factories.forEach {
            it.create(this,navController)
        }
    }
    NavHost(
        navController = navController,
        navGraph,
        modifier = modifier,
    )
}
