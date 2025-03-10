package eu.krzdabrowski.starter.basicfeature.tests

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import eu.krzdabrowski.starter.basicfeature.data.generateTestRocketsFromDomain
import eu.krzdabrowski.starter.basicfeature.presentation.composable.RocketsRoute
import eu.krzdabrowski.starter.core.presentation.MainActivity
import eu.krzdabrowski.starter.core.utils.getHiltTestViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RocketsRouteTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val testRockets = generateTestRocketsFromDomain()

    private lateinit var navController: NavController


    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            navController = rememberNavController()
            RocketsRoute(
                viewModel = composeTestRule.getHiltTestViewModel(), navController = navController
            )
        }
    }

    @Test
    fun rocketsRoute_whenHappyPath_shouldShowAllFakeRockets() {
        testRockets.forEach { rocket ->
            composeTestRule
                .onNodeWithText(rocket.name)
                .assertExists()
        }
    }
}
