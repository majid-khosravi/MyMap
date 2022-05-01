package ir.majidkhosravi.domain.usecases

import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.UseCaseParams

interface UseCase<Type, Params : UseCaseParams?> {

    operator fun invoke(param: Params?): FlowResult<Type>

}

