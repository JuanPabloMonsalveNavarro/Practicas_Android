package com.example.practica_7

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.practica_7.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sede1 = LatLng(6.265117142589887, -75.5666842506478)
        val sede2 = LatLng(6.242922771635359, -75.55546769989671)
        val sede3 = LatLng(6.248206602227418, -75.59531350422938)

        mMap.addMarker(
            MarkerOptions()
                .position(sede1)
                .title("Simons Pizza&Beer Ruta N")
                .snippet("Simons Pizza And Beer Ruta N"))
        mMap.addMarker(
            MarkerOptions()
                .position(sede2)
                .title("Simons Pizza&Beer Buenos Aires")
                .snippet("Simons Pizza&Beer Buenos Aires"))
        mMap.addMarker(
            MarkerOptions()
                .position(sede3)
                .title("Simons Pizza&Beer")
                .snippet("Simons Pizza&Beer"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sede1,15F))


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        mMap.isMyLocationEnabled = true
    }

}