package ir.majidkhosravi.mymap.ui.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import ir.majidkhosravi.common.models.VehicleModel

class VehiclesDiffCallback(
    private val oldVehiclesList: List<VehicleModel>?,
    private val newVehiclesList: List<VehicleModel>?,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldVehiclesList!!.size
    }

    override fun getNewListSize(): Int {
        return newVehiclesList!!.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldVehiclesList!![oldItemPosition].id == newVehiclesList!![newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldVehicle: VehicleModel = oldVehiclesList!![oldItemPosition]
        val newVehicle: VehicleModel = newVehiclesList!![newItemPosition]
        return (oldVehicle.coordinate === newVehicle.coordinate &&
                oldVehicle.fleetType === newVehicle.fleetType &&
                oldVehicle.heading == newVehicle.heading &&
                oldVehicle.id == newVehicle.id)
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}