package eu.krzdabrowski.starter.details.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.krzdabrowski.starter.core.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    rocketsInitialState: RocketDetailsUiState,
) : BaseViewModel<RocketDetailsUiState, RocketDetailsUiState.PartialState, RocketDetailsEvent, RocketDetailsIntent>(
    savedStateHandle,
    rocketsInitialState,
) {



    override fun mapIntents(intent: RocketDetailsIntent): Flow<RocketDetailsUiState.PartialState> {
        return flow {  }
    }

    override fun reduceUiState(
        previousState: RocketDetailsUiState,
        partialState: RocketDetailsUiState.PartialState
    ): RocketDetailsUiState = when (partialState) {
        is RocketDetailsUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false,
        )
        is RocketDetailsUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            rocketName = partialState.rocketName,
            isError = false,
        )
        is RocketDetailsUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true,
        )
    }


}