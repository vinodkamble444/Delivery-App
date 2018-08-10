package tk.andivinu.deliveryapp.ui.deliverydetail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tk.andivinu.deliveryapp.R
import tk.andivinu.deliveryapp.databinding.ActivityDeliveryDetailBinding
import tk.andivinu.deliveryapp.injection.ViewModelFactory
import android.support.v4.app.ActivityCompat
import com.bumptech.glide.Glide

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import tk.andivinu.deliveryapp.model.Delivery
import tk.andivinu.deliveryapp.utils.AT

class DeliveryDetailActivity : AppCompatActivity(), OnMapReadyCallback
        {
    private lateinit var binding: ActivityDeliveryDetailBinding
    private lateinit var viewModel: DeliveryDetailViewModel
    private lateinit var map: GoogleMap
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    val viewDeliveryIemModel = DeliveryIemViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_detail)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(DeliveryDetailViewModel::class.java)

        var intentThatStartedThisActivity = getIntent()

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            var deliveryItemId = intentThatStartedThisActivity.getIntExtra(Intent.EXTRA_TEXT, 0)
            Log.d("VINOD", "description id : " + deliveryItemId)
            viewModel.loadDeliveries(deliveryItemId)

        }
        binding.viewModel = viewDeliveryIemModel;
        viewModel.delivery.observe(this, Observer {
            delivery ->
            if (delivery != null) showDeliveryDetails(delivery)
        })
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapfragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }


    private fun showDeliveryDetails(delivery: Delivery) {
        Log.d("VINOD", "showDeliveryDetails : " + delivery.toString())
        viewDeliveryIemModel.bind(delivery)
        lat = delivery.location.lat
        lng = delivery.location.lng
        binding.descriptionTextView.text = delivery.description + AT + delivery.location.address
        Glide.with(binding.ImageView)
                .load(delivery.imageUrl)
                .into(binding.ImageView)
        setUpMap()
    }



    override fun onPause() {
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        Log.d("VINOD", ":onMapReady")
        map.uiSettings.isZoomControlsEnabled = true
        setUpMap()
    }


    private fun setUpMap() {
        Log.d("VINOD", ":setUpMap")
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        val currentLatLng = LatLng(lat, lng)
        placeMarkerOnMap(currentLatLng)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))


    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        map.addMarker(markerOptions)
    }

}


