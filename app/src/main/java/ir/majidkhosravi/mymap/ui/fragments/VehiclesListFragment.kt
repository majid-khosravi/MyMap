package ir.majidkhosravi.mymap.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.majidkhosravi.domain.usecases.MapParams
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.ui.adapter.ActionListener
import ir.majidkhosravi.mymap.ui.adapter.UiAction
import ir.majidkhosravi.mymap.ui.adapter.VehiclesAdapter
import ir.majidkhosravi.mymap.ui.base.BaseFragment
import ir.majidkhosravi.mymap.ui.dialog.ProgressDialog
import ir.majidkhosravi.mymap.viewModel.MapViewModel

@AndroidEntryPoint
class VehiclesListFragment : BaseFragment() {


    private val viewModel: MapViewModel by activityViewModels()
    private var progressDialog: ProgressDialog? = null
    private var recycler: RecyclerView? = null


    private val adapter by lazy {
        VehiclesAdapter(object : ActionListener {
            override fun onUiActionClicked(action: UiAction) {
                if (action is UiAction.VehicleSelected) {
                    val action =
                        VehiclesListFragmentDirections.actionVehiclesListFragmentToMapFragment(
                            action.selectedVehicle)
                    findNavController().navigate(action)
                }
            }
        })
    }

    override fun getLayoutResource(): Int = R.layout.fragment_vehicles_list

    override fun doOtherTasks() {
        progressDialog = ProgressDialog(requireContext())

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler = view?.findViewById(R.id.vehicles_recycler)
        recycler?.layoutManager = layoutManager
        recycler?.adapter = adapter

    }

    override fun startObservation() {
        viewModel.getList(MapParams("", "")).observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.submitItems(it)
            }
        }
        viewModel.getErrorMessage().observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.showLoading().observe(viewLifecycleOwner) {
            if (it == null || it == false) {
                recycler?.visibility = View.VISIBLE
                progressDialog?.dismiss()
            } else {
                recycler?.visibility = View.GONE
                progressDialog?.show()
            }
        }
    }
}