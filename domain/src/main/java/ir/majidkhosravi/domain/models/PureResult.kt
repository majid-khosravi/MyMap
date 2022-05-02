package ir.majidkhosravi.domain.models

import kotlinx.coroutines.flow.Flow


/**
 * This class was created to help us for mapping all [ApiResult] into this
 * The PureResult is a kind of state model that transferred data from repositories to UseCases and ViewModels
 *
 * 1- [Success], in this situation the result definitely has a correct value
 * 2- [Error], in this state something went wrong. then we'll raise a proper [FinalErrorModel]
 *
 * with this difference that the PureResult has an extra state to handle [Loading] situation
 *
 */

sealed class PureResult<out T> {

    data class Success<T>(val value: T) : PureResult<T>()
    object Loading : PureResult<Nothing>()
    data class Error(val error: FinalErrorModel) : PureResult<Nothing>()

}

data class FinalErrorModel(val message: String?)


typealias FlowResult<T> = Flow<PureResult<T>>