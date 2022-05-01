package ir.majidkhosravi.data.apiservices

import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.data.mappers.safeCall
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.data.utils.NetworkConstants
import ir.majidkhosravi.domain.models.UseCaseParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRemoteDateSource @Inject constructor(
    private val apiService: ApiService,
    private val globalDispatcher: GlobalDispatcher,
): RemoteDateSource {

    override suspend fun getVehicleResponse(param: UseCaseParams?): ApiResult<PoiList> =
        safeCall(globalDispatcher) {
            return@safeCall apiService.getVehiclesList(NetworkConstants.VEHICLES_ENDPOINT)
        }

}