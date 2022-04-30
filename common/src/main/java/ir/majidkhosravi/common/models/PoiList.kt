package ir.majidkhosravi.common.models

import com.google.gson.annotations.SerializedName

data class PoiList(@SerializedName("poiList") val list: List<VehicleModel>)