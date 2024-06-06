package eu.krzdabrowski.starter.details.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Immutable
@Parcelize
data class RocketDetailsUiState @Inject constructor(
    val isLoading: Boolean = false,
    val rocketName: String,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val rocketName: String) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}