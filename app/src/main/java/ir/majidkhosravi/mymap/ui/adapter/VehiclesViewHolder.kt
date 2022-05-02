package ir.majidkhosravi.mymap.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.utils.UiAction
import ir.majidkhosravi.mymap.utils.bindVehicleIcon
import ir.majidkhosravi.mymap.utils.bindVehicleNumber
import ir.majidkhosravi.mymap.utils.bindVehicleTitle

/**
 * In this [ViewHolder], I'm going to display a plain view that includes 3 components
 *
 * 1- In the first, I'll show the vehicle type as a title text
 * 2- After that, I'll put the id of the vehicle instead of the number 😉
 * 3- In the last, I'll display a proper icon for taxi vehicles and pooling services.
 *
 * Also, I've created three extension functions for these values.
 */


class VehiclesViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(model: VehicleModel, callback: (UiAction) -> Unit) {
        view.findViewById<AppCompatTextView>(R.id.text).bindVehicleTitle(model)
        view.findViewById<AppCompatImageView>(R.id.image).bindVehicleIcon(model)
        view.findViewById<AppCompatTextView>(R.id.sub_title).bindVehicleNumber(model)
        view.setOnClickListener {
            callback(UiAction.VehicleSelected(model))
        }
    }
}