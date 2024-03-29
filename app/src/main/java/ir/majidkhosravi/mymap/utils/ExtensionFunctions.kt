package ir.majidkhosravi.mymap.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R

/**
 * I've defined some extension functions to bind some values of vehicles item in the list
 */


fun AppCompatImageView.bindVehicleIcon(vehicle: VehicleModel) {
    vehicle.fleetType?.let {
        if (it == "POOLING") {
        setImageResource(R.drawable.ic_pooling)
    } else {
        setImageResource(R.drawable.ic_taxi)
    }
    }
}

fun AppCompatTextView.bindVehicleTitle(vehicle: VehicleModel) {
    text = vehicle.fleetType
}

fun AppCompatTextView.bindVehicleNumber(vehicle: VehicleModel) {
    text = resources.getString(R.string.vehicle_number, vehicle.id)
}