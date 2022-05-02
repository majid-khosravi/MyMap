package ir.majidkhosravi.domain.usecases

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.UseCaseParams
import ir.majidkhosravi.domain.repositories.MapRepository
import javax.inject.Inject

/**
 *  There is we have a child of [UseCase], this UseCase takes a flowable object of vehicles list from [MapRepository]
 *  and also gives that to [MapViewModel]
 */

class MapUseCase @Inject constructor(
    private val repository: MapRepository,
) : UseCase<PoiList, MapParams> {

    override operator fun invoke(param: MapParams?): FlowResult<PoiList> {
        return repository.getVehiclesList(param)
    }

}

data class MapParams(val lat1: Double, val lon1: Double, val lat2: Double, val lon2: Double) : UseCaseParams()