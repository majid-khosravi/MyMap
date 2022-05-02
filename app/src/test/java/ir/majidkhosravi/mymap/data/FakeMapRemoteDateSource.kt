package ir.majidkhosravi.mymap.data

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.data.apiservices.RemoteDateSource
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.domain.models.UseCaseParams
import ir.majidkhosravi.domain.usecases.MapParams

/**
 * I've created a mock [RemoteDateSource] here
 */

class FakeMapRemoteDateSource(private val poiList: PoiList?) : RemoteDateSource {

    override suspend fun getVehicleResponse(param: MapParams?): ApiResult<PoiList> {
        if (poiList == null || poiList.list.isEmpty() || param == null) {
            return ApiResult.Failure(VehiclesMockProvider.error)
        }
        return ApiResult.Success(poiList)
    }

}