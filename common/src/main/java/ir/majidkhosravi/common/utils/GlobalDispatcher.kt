package ir.majidkhosravi.common.utils

import kotlinx.coroutines.CoroutineDispatcher


/**
 * GlobalDispatcher is a common utils to create and keep instance of [CoroutineDispatcher] to use in other modules
 */


data class GlobalDispatcher(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher,
)