package eu.krzdabrowski.starter.basicfeature.presentation

import eu.krzdabrowski.starter.basicfeature.presentation.model.RocketDisplayable

sealed class RocketsIntent {
    data object RefreshRockets : RocketsIntent()
    data class RocketClicked(val rocketName: String) : RocketsIntent()
}