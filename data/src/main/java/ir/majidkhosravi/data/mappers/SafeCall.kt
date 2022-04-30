package ir.majidkhosravi.data.mappers

import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.data.models.ErrorModel
import ir.majidkhosravi.domain.models.FinalErrorModel
import ir.majidkhosravi.domain.models.FlowResult
import ir.majidkhosravi.domain.models.PureResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext


suspend fun <T> safeCall(
    globalDispatcher: GlobalDispatcher,
    callback: suspend () -> T,
): ApiResult<T> =
    withContext(globalDispatcher.io) {
        return@withContext try {
            ApiResult.Success(callback())
        } catch (e: Exception) {
            ApiResult.Failure(ErrorModel(e.message))
        }
    }


fun <T> flowResult(callback: suspend () -> ApiResult<T>): FlowResult<T> {
    return flow {
        when (val apiResult = callback()) {
            is ApiResult.Success -> emit(PureResult.Success(apiResult.value))
            is ApiResult.Failure -> emit(PureResult.Error(FinalErrorModel(apiResult.error.toString())))
        }
    }.onStart {
        emit(PureResult.Loading)
    }
}