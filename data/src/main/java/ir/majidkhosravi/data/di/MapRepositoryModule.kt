package ir.majidkhosravi.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.majidkhosravi.data.repositories.MapRepositoryImp
import ir.majidkhosravi.domain.repositories.MapRepository


@Module
@InstallIn(ActivityRetainedComponent::class)
interface MapRepositoryModule {

    @Binds
    @ActivityRetainedScoped
    fun bindMapRepository(
        repositoryImpl: MapRepositoryImp,
    ): MapRepository
}