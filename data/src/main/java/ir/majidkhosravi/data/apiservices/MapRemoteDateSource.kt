package ir.majidkhosravi.data.apiservices

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.data.mappers.safeCall
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.domain.usecases.MapParams
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The MapRemoteDateSource is one of the most important classes to fetch API response
 * This class must implement the [RemoteDateSource] with its abstracted function
 * in this case we have a function to get the response of the vehicle
 */

@Singleton
class MapRemoteDateSource @Inject constructor(
    private val apiService: ApiService,
    private val globalDispatcher: GlobalDispatcher,
) : RemoteDateSource {

    override suspend fun getVehicleResponse(param: MapParams?): ApiResult<PoiList> =
        safeCall(globalDispatcher) {
                return@safeCall apiService.getVehiclesList(
                    p1Lat = param?.lat1,
                    p1Lon = param?.lon1,
                    p2Lat = param?.lat2,
                    p2Lon = param?.lon2
                )
        }

}