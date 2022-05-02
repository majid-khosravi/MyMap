package ir.majidkhosravi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.majidkhosravi.data.apiservices.ApiService
import retrofit2.Retrofit

/**
 * In this module we have provided an instance of the [ApiService]
 */

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun bindApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}