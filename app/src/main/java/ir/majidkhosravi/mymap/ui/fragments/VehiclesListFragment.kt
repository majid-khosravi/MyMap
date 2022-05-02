package ir.majidkhosravi.mymap.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.majidkhosravi.data.utils.NetworkConstants
import ir.majidkhosravi.domain.usecases.MapParams
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.utils.UiAction
import ir.majidkhosravi.mymap.ui.adapter.VehiclesAdapter
import ir.majidkhosravi.mymap.ui.base.BaseFragment
import ir.majidkhosravi.mymap.ui.dialog.ProgressDialog
import ir.majidkhosravi.mymap.viewModel.MapViewModel


/**
 * This Fragment has used of [dagger.hilt.android.AndroidEntryPoint] annotation of the Hilt DI library
 * this annotation marks an Android component class to be setup for injection
 * with the standard [Hilt] Dagger Android components.
 */


/**
 * We've created an instance of vehicles adapter by items that we observed from ViewModel
 * and when this fetching is not completed, we must show a progress dialog as a [ir.majidkhosravi.domain.models.PureResult.Loading] state
 * and when an [ir.majidkhosravi.domain.models.PureResult.Error] has been raised we have to show a toast message to announce to the user what happened
 */


/**
 * Also when the user selects an item in the list we're going to go [MapFragment]
 * and show to them the locations of the vehicles on GoogleMap
 *
 * As you know, to share a [MapViewModel] between two or more fragments, we must use the activity lifecycle owner
 * to generate and inject that ViewModel by using [activityViewModels] inline function.
 */


@AndroidEntryPoint
class VehiclesListFragment : BaseFragment() {


    private val viewModel: MapViewModel by activityViewModels()
    private var progressDialog: ProgressDialog? = null
    private var recycler: RecyclerView? = null


    private val adapter by lazy {
        VehiclesAdapter { action ->
            if (action is UiAction.VehicleSelected) {
                findNavController().navigate(
                    VehiclesListFragmentDirections.actionVehiclesListFragmentToMapFragment(action.selectedVehicle)
                )
            }
        }
    }

    override fun getLayoutResource(): Int = R.layout.fragment_vehicles_list

    override fun doOtherTasks() {
        viewModel.fetchVehiclesList(
            MapParams(
                lat1 = NetworkConstants.p1Lat,
                lon1 = NetworkConstants.p1Lon,
                lat2 = NetworkConstants.p2Lat,
                lon2 = NetworkConstants.p2Lon
            )
        )
        progressDialog = ProgressDialog(requireContext())

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler = view?.findViewById(R.id.vehicles_recycler)
        recycler?.layoutManager = layoutManager
        recycler?.adapter = adapter

    }

    override fun startObservation() {
        viewModel.adapterRows.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.submitItems(it)
            }
        }
        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            action?.let {
                if (action is UiAction.ShowLoading) {
                    if (action.show) {
                        recycler?.visibility = View.GONE
                        progressDialog?.show()
                    } else {
                        recycler?.visibility = View.VISIBLE
                        progressDialog?.dismiss()
                    }
                } else if (action is UiAction.ShowErrorMessage)
                    Toast.makeText(requireContext(), action.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

}