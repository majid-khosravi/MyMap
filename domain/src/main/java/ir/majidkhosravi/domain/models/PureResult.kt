package ir.majidkhosravi.domain.models

import kotlinx.coroutines.flow.Flow

sealed class PureResult<out T> {

    data class Success<T>(val value: T) : PureResult<T>()
    object Loading : PureResult<Nothing>()
    data class Error(val error: FinalErrorModel) : PureResult<Nothing>()

}

data class FinalErrorModel(val message: String?)


typealias FlowResult<T> = Flow<PureResult<T>>