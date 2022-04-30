package ir.majidkhosravi.common.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("fleetType")
    val fleetType: String?,
    @SerializedName("heading")
    val heading: Float,
    @SerializedName("coordinate")
    val coordinate: CoordinateModel
    ):Parcelable
