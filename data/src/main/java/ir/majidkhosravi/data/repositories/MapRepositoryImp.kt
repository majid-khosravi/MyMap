package ir.majidkhosravi.data.repositories

import ir.majidkhosravi.data.apiservices.MapRemoteDateSource
import ir.majidkhosravi.data.mappers.flowResult
import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.repositories.MapRepository
import ir.majidkhosravi.domain.usecases.MapParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepositoryImp @Inject constructor(
    private val mapRemoteDateSource: MapRemoteDateSource,
) : MapRepository {


    override fun getVehiclesList(param: MapParams?): FlowResult<PoiList> =
        flowResult {
            mapRemoteDateSource.getVehicleResponse(param)
        }

}