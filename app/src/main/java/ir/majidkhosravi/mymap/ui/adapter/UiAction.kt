package ir.majidkhosravi.mymap.ui.adapter

import ir.majidkhosravi.common.models.VehicleModel

sealed interface UiAction {

    class VehicleSelected(val selectedVehicle: VehicleModel) : UiAction

    class ShowLoading(val show: Boolean) : UiAction

    class ShowErrorMessage(val error: String) : UiAction

}