package ir.majidkhosravi.common.utils

import kotlinx.coroutines.CoroutineDispatcher

data class GlobalDispatcher(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher,
)