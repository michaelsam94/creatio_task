package eu.krzdabrowski.starter.basicfeature.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.krzdabrowski.starter.basicfeature.domain.usecase.GetRocketsUseCase
import eu.krzdabrowski.starter.basicfeature.domain.usecase.RefreshRocketsUseCase
import eu.krzdabrowski.starter.basicfeature.presentation.mapper.toPresentationModel
import eu.krzdabrowski.starter.basicfeature.presentation.model.RocketDisplayable
import eu.krzdabrowski.starter.core.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase,
    private val refreshRocketsUseCase: RefreshRocketsUseCase,
    savedStateHandle: SavedStateHandle,
    rocketsInitialState: RocketsUiState,
) : BaseViewModel<RocketsUiState, RocketsUiState.PartialState, RocketsEvent, RocketsIntent>(
    savedStateHandle,
    rocketsInitialState,
) {
    init {
        observeRockets()
    }

    override fun mapIntents(intent: RocketsIntent): Flow<RocketsUiState.PartialState> = when (intent) {
        is RocketsIntent.RefreshRockets -> refreshRockets()
        is RocketsIntent.RocketClicked -> rocketClicked(intent.rocketName)
    }

    override fun reduceUiState(
        previousState: RocketsUiState,
        partialState: RocketsUiState.PartialState,
    ): RocketsUiState = when (partialState) {
        is RocketsUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false,
        )
        is RocketsUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            rockets = partialState.list,
            isError = false,
        )
        is RocketsUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true,
        )
    }

    private fun observeRockets() = acceptChanges(
        getRocketsUseCase()
            .map { result ->
                result.fold(
                    onSuccess = { rocketList ->
                        RocketsUiState.PartialState.Fetched(rocketList.map { it.toPresentationModel() })
                    },
                    onFailure = {
                        RocketsUiState.PartialState.Error(it)
                    },
                )
            }
            .onStart {
                emit(RocketsUiState.PartialState.Loading)
            },
    )

    private fun refreshRockets(): Flow<RocketsUiState.PartialState> = flow<RocketsUiState.PartialState> {
        refreshRocketsUseCase()
            .onFailure {
                emit(RocketsUiState.PartialState.Error(it))
            }
    }.onStart {
        emit(RocketsUiState.PartialState.Loading)
    }

    private fun rocketClicked(rocketName: String): Flow<RocketsUiState.PartialState> = flow {
       setEvent(RocketsEvent.OpenRocketDetails(rocketName))
    }
}