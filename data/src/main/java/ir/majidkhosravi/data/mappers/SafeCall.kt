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

/**
 * We have two unit functions here that are providing some behaviors about synchronization:
 *
 * 1- [safeCall] is a unit function that makes an instance of [ApiResult] and returns it in the [CoroutineDispatcher.IO]]
 *
 * 2- [flowResult] is a unit function the same, but with the difference that this function takes an object of [ApiResult]
 * and then returns the proper state of the result as a flowable object.
 * This function is the most important to map the [ApiResult] into FlowResult.
 * The [FlowResult] is the type that the repository has expected.
 *
 */

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