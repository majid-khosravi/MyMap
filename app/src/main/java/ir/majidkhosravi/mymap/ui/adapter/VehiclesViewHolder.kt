package ir.majidkhosravi.mymap.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.utils.bindVehicleIcon
import ir.majidkhosravi.mymap.utils.bindVehicleNumber
import ir.majidkhosravi.mymap.utils.bindVehicleTitle

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