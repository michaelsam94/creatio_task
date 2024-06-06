package eu.krzdabrowski.starter.basicfeature.data

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import eu.krzdabrowski.starter.core.utils.resultOf
import eu.krzdabrowski.starter.details.data.di.RocketDetailsModule
import kotlinx.coroutines.flow.flowOf

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RocketDetailsModule::class],
)
internal object FakeRocketDetailsModule {

}