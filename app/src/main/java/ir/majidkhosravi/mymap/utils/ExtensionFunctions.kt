package ir.majidkhosravi.mymap.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R

fun AppCompatImageView.bindVehicleIcon(vehicle: VehicleModel) {
    if (vehicle.fleetType == "POOLING") {
        setImageResource(R.drawable.ic_pooling)
    } else {
        setImageResource(R.drawable.ic_taxi)
    }
}

fun AppCompatTextView.bindVehicleTitle(vehicle: VehicleModel) {
    text = vehicle.fleetType
}

fun AppCompatTextView.bindVehicleNumber(vehicle: VehicleModel) {
    text = resources.getString(R.string.vehicle_number, vehicle.id)
}