package eu.krzdabrowski.starter.basicfeature.tests

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import eu.krzdabrowski.starter.details.presentation.RocketDetailsUiState
import eu.krzdabrowski.starter.details.presentation.composable.RocketDetailsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RocketDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()




    @Before
    fun setUp() {
    }

    @Test
    fun rocketsScreen_shouldShowContent() {
        setUpComposable(
            RocketDetailsUiState(
                rocketName = "hello",
                isError = false,
                isLoading = false,
            ),
        )

        composeTestRule
            .onNodeWithText("hello")
            .assertExists()
    }

    @Test
    fun rocketsScreen_shouldShowEmptyContent() {
        setUpComposable(
            RocketDetailsUiState(
                rocketName = "",
                isError = false,
                isLoading = false,
            ),
        )

        composeTestRule
            .onNodeWithText("")
            .assertExists()
    }



    private fun setUpComposable(
        rocketsUiState: RocketDetailsUiState,
    ) {
        composeTestRule.setContent {
            RocketDetailsScreen(
                uiState = rocketsUiState,
                onIntent = { },
            )
        }
    }
}
