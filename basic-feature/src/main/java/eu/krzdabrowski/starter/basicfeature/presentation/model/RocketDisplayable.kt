package eu.krzdabrowski.starter.basicfeature.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class RocketDisplayable(
    val id: String,
    val name: String,
    val costPerLaunchInMillions: Int,
    val firstFlightDate: String,
    val heightInMeters: Int,
    val weightInTonnes: Int,
    val wikiUrl: String,
    val imageUrl: String,
    val country: String
) : Parcelable
