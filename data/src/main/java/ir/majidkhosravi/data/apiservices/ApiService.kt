package ir.majidkhosravi.data.apiservices

import ir.majidkhosravi.common.models.PoiList
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The endpoints are defined inside of an interface using special [Retrofit]
 * annotations to encode details about the parameters and request method.
 */

interface ApiService {

    @GET("?")
    suspend fun getVehiclesList(
        @Query("p1Lat") p1Lat: Double?,
        @Query("p1Lon") p1Lon: Double?,
        @Query("p2Lat") p2Lat: Double?,
        @Query("p2Lon") p2Lon: Double?,
    ): PoiList

}