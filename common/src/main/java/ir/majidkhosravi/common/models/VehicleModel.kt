package ir.majidkhosravi.common.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class VehicleModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("fleetType")
    val fleetType: String?,
    @SerializedName("heading")
    val heading: Float,
    @SerializedName("coordinate")
    val coordinate: CoordinateModel,

    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readFloat(),
        TODO("coordinate"))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(fleetType)
        parcel.writeFloat(heading)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleModel> {
        override fun createFromParcel(parcel: Parcel): VehicleModel {
            return VehicleModel(parcel)
        }

        override fun newArray(size: Int): Array<VehicleModel?> {
            return arrayOfNulls(size)
        }
    }
}

