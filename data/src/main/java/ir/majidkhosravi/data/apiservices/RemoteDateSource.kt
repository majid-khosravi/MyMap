package ir.majidkhosravi.data.apiservices

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.domain.usecases.MapParams

/**
 * We have developed this interface to shape our sub-DateSource and in this case
 * we had a function to fetch vehicles list of the remote API
 * It returns a response in the state of [ApiResult]
 */

interface RemoteDateSource {

    suspend fun getVehicleResponse(param: MapParams?): ApiResult<PoiList>
}