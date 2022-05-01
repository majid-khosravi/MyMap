package ir.majidkhosravi.mymap.data

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.data.apiservices.RemoteDateSource
import ir.majidkhosravi.data.mappers.flowResult
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.repositories.MapRepository
import ir.majidkhosravi.domain.usecases.MapParams

class FakeMapRepository(private val datasource: RemoteDateSource) : MapRepository {

    override fun getVehiclesList(param: MapParams?): FlowResult<PoiList> =
        flowResult { datasource.getVehicleResponse(param) }

}