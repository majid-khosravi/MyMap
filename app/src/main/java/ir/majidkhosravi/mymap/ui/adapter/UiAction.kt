package ir.majidkhosravi.mymap.ui.adapter

import ir.majidkhosravi.common.models.VehicleModel

sealed interface UiAction {

    class VehicleSelected(val selectedVehicle: VehicleModel) : UiAction

}