package ir.majidkhosravi.domain.usecases

import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.UseCaseParams

/**
 *  UseCases are the business logic executors that fetch data from data sources either remote or local
 *  and gives it back to the requester in our case it would be the app layer.
 */

interface UseCase<Type, Params : UseCaseParams?> {

    operator fun invoke(param: Params?): FlowResult<Type>

}

