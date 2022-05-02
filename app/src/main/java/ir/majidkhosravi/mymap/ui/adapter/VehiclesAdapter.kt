package ir.majidkhosravi.mymap.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.utils.UiAction

/**
 * I've defined this Adapter in a simple way that exists to implement a [RecyclerView.Adapter]
 * There is only one type of ViewHolders that I've used to show Vehicle rows
 * Also, I had a [callback] function to catch the whole of click events on rows.
 * This callback injects whit proper object into ViewHolder and then will use it there.
 */


class VehiclesAdapter(private val callback: (UiAction) -> Unit) :
    RecyclerView.Adapter<VehiclesViewHolder>() {

    private var itemsRows: MutableList<VehicleModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(list: List<VehicleModel>) {
        itemsRows.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        return VehiclesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicles, parent, false))
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        itemsRows[position].let {
            holder.bind(it, callback)
        }
    }

    override fun getItemCount(): Int = itemsRows.size


}