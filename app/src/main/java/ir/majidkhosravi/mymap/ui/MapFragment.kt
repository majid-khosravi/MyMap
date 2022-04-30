package ir.majidkhosravi.mymap.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.ui.base.BaseFragment
import ir.majidkhosravi.mymap.viewModel.MapViewModel

@AndroidEntryPoint
class MapFragment : BaseFragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private val viewModel: MapViewModel by activityViewModels()

    private lateinit var mMap: GoogleMap
    private var vehiclesList: ArrayList<VehicleModel>? = null
    private var selectedVehicle: VehicleModel? = null


    override fun getLayoutResource() = R.layout.fragment_map


    override fun doOtherTasks() {
        selectedVehicle = MapFragmentArgs.fromBundle(requireArguments()).selectedVehicle
        if (vehiclesList.isNullOrEmpty()) vehiclesList?.addAll(viewModel.adapterRows.value!!)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        mMap.setInfoWindowAdapter(CustomAdapter(requireContext()))


        selectedVehicle?.let {
            val latLng = LatLng(it.coordinate.latitude, it.coordinate.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title("MY MAP"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            vehiclesList?.let {list ->
                bindVehicles(list)
            }

        } ?: mMap.setOnCameraIdleListener(this)

    }

    private fun bindVehicles(vehicles: List<VehicleModel>) {
        vehicles.forEach { bindVehicle(it) }
    }

    private fun bindVehicle(vehicle: VehicleModel) {
        val latlng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
        mMap.apply {
            addMarker(MarkerOptions().position(latlng).title(vehicle.fleetType))
        }
    }


    class CustomAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
        override fun getInfoWindow(marker: Marker): View? {
            val view = LayoutInflater.from(context).inflate(R.layout.map_info_window, null)
            return view
        }

        override fun getInfoContents(marker: Marker): View? {
//            marker.setIcon()
            return null
        }

    }



    override fun onCameraIdle() {
        val bounds = mMap.projection.visibleRegion.latLngBounds

    }


}