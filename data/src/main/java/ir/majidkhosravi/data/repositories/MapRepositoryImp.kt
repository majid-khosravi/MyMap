package ir.majidkhosravi.data.repositories

import ir.majidkhosravi.data.apiservices.RemoteDateSource
import ir.majidkhosravi.data.mappers.flowResult
import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.UseCaseParams
import ir.majidkhosravi.domain.repositories.MapRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepositoryImp @Inject constructor(
    private val remoteDateSource: RemoteDateSource,
) : MapRepository {


    override fun getVehiclesList(param: UseCaseParams): FlowResult<PoiList> =
        flowResult {
            remoteDateSource.getVehicleResponse(param)
        }

}