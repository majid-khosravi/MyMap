package ir.majidkhosravi.mymap.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R

class VehiclesAdapter(private val listener: ActionListener) :
    RecyclerView.Adapter<VehiclesViewHolder>() {

    private var itemsRows: MutableList<VehicleModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(list: List<VehicleModel>) {
        itemsRows.apply {
            val diffCallback = VehiclesDiffCallback(itemsRows, list)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            clear()
            addAll(list)
            diffResult.dispatchUpdatesTo(this@VehiclesAdapter)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        return VehiclesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicles, parent, false), listener)
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        itemsRows[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = itemsRows.size


}