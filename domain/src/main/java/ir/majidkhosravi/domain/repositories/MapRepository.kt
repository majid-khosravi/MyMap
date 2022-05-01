package ir.majidkhosravi.domain.repositories

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.usecases.MapParams

interface MapRepository {
    fun getVehiclesList(param: MapParams?): FlowResult<PoiList>
}