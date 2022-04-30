package ir.majidkhosravi.mymap.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R

class VehiclesViewHolder(view: View, private val listener: ActionListener) :
    RecyclerView.ViewHolder(view) {

    private val textView: AppCompatTextView = view.findViewById(R.id.text)
    private lateinit var vehicleModel: VehicleModel

    init {
        view.setOnClickListener {
            listener.onUiActionClicked(UiAction.VehicleSelected(vehicleModel))
        }
    }


    fun bind(model: VehicleModel) {
        this.vehicleModel = model
        textView.text = model.fleetType
    }
}