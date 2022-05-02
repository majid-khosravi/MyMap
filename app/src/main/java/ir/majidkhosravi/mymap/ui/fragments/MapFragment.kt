package ir.majidkhosravi.mymap.ui.fragments

import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.ui.base.BaseFragment
import ir.majidkhosravi.mymap.viewModel.MapViewModel

/**
 * This fragment helps us to show a [com.google.android.gms.maps.SupportMapFragment] component
 *
 * The main difference when using this fragment instead of the fragments are:
 * it includes an [com.google.android.gms.maps.OnMapReadyCallback] and also it's a necessary callback that
 * gives me an instance of [com.google.android.gms.maps.GoogleMap]
 */

/**
 * This [androidx.fragment.app.Fragment] has used of [dagger.hilt.android.AndroidEntryPoint] annotation of the Hilt DI library
 * this annotation marks an Android component class to be setup for injection
 * with the standard Hilt Dagger Android components.
 *
 */

/**
 * We had an injected item of a vehicle of the previous fragment
 * There is a [MapViewModel] that is shared between this fragment and [VehiclesListFragment]
 * and we are going take the list of vehicles of that
 * after that we need to show the whole vehicle's location on the map
 *
 * As you know, to share a ViewModel between two or more fragments, we must use the activity lifecycle owner
 * to generate and inject that ViewModel by using [activityViewModels] inline function.
 *
 * when the user selects one of them, we have to zoom to it and show that location center of the map.
 */


@AndroidEntryPoint
class MapFragment : BaseFragment(), OnMapReadyCallback {

    private val viewModel: MapViewModel by activityViewModels()

    private lateinit var mMap: GoogleMap
    private var vehiclesList: ArrayList<VehicleModel>? = ArrayList()
    private var selectedVehicle: VehicleModel? = null


    override fun getLayoutResource() = R.layout.fragment_map


    override fun doOtherTasks() {
        selectedVehicle = MapFragmentArgs.fromBundle(requireArguments()).selectedVehicle
        viewModel.adapterRows.value?.let {
            vehiclesList?.addAll(it)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        selectedVehicle?.let {
            val latLng = LatLng(it.coordinate.latitude, it.coordinate.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title(it.fleetType + "\t" + it.id))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            vehiclesList?.let { list ->
                bindVehicles(list)
            }
        }

    }

    private fun bindVehicles(vehicles: List<VehicleModel>) {
        vehicles.forEach { bindVehicle(it) }
    }

    private fun bindVehicle(vehicle: VehicleModel) {
        val latlng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
        mMap.apply {
            addMarker(MarkerOptions().position(latlng).title(vehicle.fleetType + "\t" + vehicle.id))
        }
    }


}