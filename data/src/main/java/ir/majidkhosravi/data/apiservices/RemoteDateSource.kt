package ir.majidkhosravi.data.apiservices

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.domain.usecases.MapParams

interface RemoteDateSource {

    suspend fun getVehicleResponse(param: MapParams?): ApiResult<PoiList>
}