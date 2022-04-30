package ir.majidkhosravi.data.models

sealed class ApiResult<out T> {

    data class Success<T>(val value: T) : ApiResult<T>()
    data class Failure(val error: ErrorModel) : ApiResult<Nothing>()

}

data class ErrorModel(val message: String?)