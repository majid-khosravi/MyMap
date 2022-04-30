package ir.majidkhosravi.mymap.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.majidkhosravi.domain.usecases.MapParams
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.databinding.ActivityMapsBinding
import ir.majidkhosravi.mymap.viewModel.MapViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class MapsActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    val viewModel: MapViewModel by viewModels()

/*
    private object onMapReady : OnMapReadyCallback {
        override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap

            // Add a marker in Sydney and move the camera
            val sydney = LatLng(-34.0, 151.0)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }

    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(onMapReady)
        viewModel.getList(MapParams("", ""))
        viewModel.items.observe(this) {
            Log.e("Majid", it.toString())
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /*override fun onMapReady(googleMap: GoogleMap) {

    }*/
}