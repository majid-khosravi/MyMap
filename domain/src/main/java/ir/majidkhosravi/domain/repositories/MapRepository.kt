package ir.majidkhosravi.domain.repositories

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.usecases.MapParams

/**
 * In the domain layer it's possible that we have many different repositories.
 * Then we have to define an interface of them and after that create a sub class in the data layer
 *
 * In this case we have created an interface of [MapRepository] along with a function that named [getVehiclesList]
 */


interface MapRepository {
    fun getVehiclesList(param: MapParams?): FlowResult<PoiList>
}