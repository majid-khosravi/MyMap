package ir.majidkhosravi.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.majidkhosravi.common.utils.GlobalDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * In this module I've implemented some common provider,
 *  for instance I've defined the {@link GlobalDispatcher} provider to use in other modules
 */


@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun provideGlobalDispatcher() = GlobalDispatcher(
        main = Dispatchers.Main,
        io = Dispatchers.IO,
        default = Dispatchers.Default
    )
}