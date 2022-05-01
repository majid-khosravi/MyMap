package ir.majidkhosravi.domain.usecases

import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.UseCaseParams
import ir.majidkhosravi.domain.repositories.MapRepository
import javax.inject.Inject

class MapUseCase @Inject constructor(
    private val repository: MapRepository,
) : UseCase<PoiList, MapParams> {

    override operator fun invoke(param: MapParams?): FlowResult<PoiList> {
        return repository.getVehiclesList(param)
    }

}

data class MapParams(val lat: String, val lon: String) : UseCaseParams()