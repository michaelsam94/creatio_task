package eu.krzdabrowski.starter.details.presentation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import eu.krzdabrowski.starter.details.presentation.RocketDetailsUiState
import eu.krzdabrowski.starter.details.presentation.RocketDetailsNavigationFactory
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
internal object RocketDetailsModule {

    @Provides
    fun provideInitialRocketDetailUiState(): RocketDetailsUiState = RocketDetailsUiState(false,"",false)


    @Module
    @InstallIn(SingletonComponent::class)
    internal interface RocketDetailsSingltonModule {

        @Singleton
        @Binds
        @IntoSet
        fun bindRocketsNavigationFactory(factory: RocketDetailsNavigationFactory): NavigationFactory
    }

}


