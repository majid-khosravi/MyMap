package ir.majidkhosravi.domain.repositories

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.UseCaseParams

interface MapRepository {
    fun getVehiclesList(param: UseCaseParams?): FlowResult<PoiList>
}