package ir.majidkhosravi.data.apiservices

import ir.majidkhosravi.common.models.PoiList
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {

    @GET
    suspend fun getVehiclesList(@Url url: String): PoiList

}