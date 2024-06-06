package eu.krzdabrowski.starter.details.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import eu.krzdabrowski.starter.details.presentation.composable.RocketDetailsRoute
import eu.krzdabrowski.starter.core.navigation.NavigationDestination
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import eu.krzdabrowski.starter.core.navigation.NavigationManager
import eu.krzdabrowski.starter.details.presentation.composable.RocketDetailsScreen
import javax.inject.Inject


class RocketDetailsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder,navController: NavController) {
        builder.composable(route = "${NavigationDestination.RocketDetails.route}/{rocketName}",
            arguments = listOf(navArgument("rocketName") { type = NavType.StringType })) {
             backStackEntry ->
                val rocketName = backStackEntry.arguments?.getString("rocketName") ?: return@composable
                RocketDetailsRoute(rocketName)
        }

    }
}
