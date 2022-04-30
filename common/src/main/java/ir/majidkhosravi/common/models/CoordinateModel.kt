package ir.majidkhosravi.common.models

import com.google.gson.annotations.SerializedName


data class CoordinateModel(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
)