package ir.majidkhosravi.data.models

/**
 * The ApiResult is a generic and basic class of API results in our DataSources
 * This sealed class has two different states:
 *
 * 1- [Success], in this situation the result definitely has a correct value
 * 2- [Failure], in this state something went wrong. then we'll raise a proper [ErrorModel]
 */


sealed class ApiResult<out T> {

    data class Success<T>(val value: T) : ApiResult<T>()
    data class Failure(val error: ErrorModel) : ApiResult<Nothing>()

}

data class ErrorModel(val message: String?)