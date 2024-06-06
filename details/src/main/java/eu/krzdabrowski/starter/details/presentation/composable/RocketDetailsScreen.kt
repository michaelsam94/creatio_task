package eu.krzdabrowski.starter.details.presentation.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.krzdabrowski.starter.basicfeature.R
import eu.krzdabrowski.starter.details.presentation.RocketDetailsIntent
import eu.krzdabrowski.starter.details.presentation.RocketDetailsUiState
import eu.krzdabrowski.starter.details.presentation.RocketDetailsViewModel


@Composable
fun RocketDetailsRoute(
    rocketName: String,
    viewModel: RocketDetailsViewModel = hiltViewModel(),
) {
    val uiState = RocketDetailsUiState(false,rocketName,false)

    RocketDetailsScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
    )
}


@Composable
fun RocketDetailsScreen(
    uiState: RocketDetailsUiState,
    onIntent: (RocketDetailsIntent) -> Unit,
) {
    Text(text = uiState.rocketName)
}