package ir.majidkhosravi.mymap.utils

import ir.majidkhosravi.common.models.VehicleModel


/**
 * In summarized meaning, the [UiAction] class can help us to pass and transformation of the UI states,
 * view click listeners and any type that needs to observe in views.
 */

sealed interface UiAction {

    class VehicleSelected(val selectedVehicle: VehicleModel) : UiAction

    class ShowLoading(val show: Boolean) : UiAction

    class ShowErrorMessage(val error: String) : UiAction

}