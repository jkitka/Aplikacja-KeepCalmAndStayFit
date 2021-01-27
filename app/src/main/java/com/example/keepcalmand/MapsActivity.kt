package com.example.keepcalmand


import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.keepcalmand.Model.MyPlaces
import com.example.keepcalmand.Remote.Common.Common
import com.example.keepcalmand.Remote.IGoogleAPIService
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN
import kotlinx.android.synthetic.main.activity_maps.*
import retrofit2.Call
import retrofit2.Response
import java.lang.StringBuilder
import javax.security.auth.callback.Callback

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latitude:Double=0.toDouble()
    private var longitude:Double=0.toDouble()
    private lateinit var mLastLocation:Location
    private var mMarker: Marker?=null

    //Location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    companion object{
        private const val MY_PERMISION_CODE: Int=1000
    }

    lateinit var mService:IGoogleAPIService

    internal lateinit var currentPlace: MyPlaces
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Init Service
        mService = Common.googleAPIService

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(checkLocationsPermission()) {
                buildLocationRequest()
                buildLocationCallBack()

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
                }
            }
              else{
                buildLocationRequest()
                buildLocationCallBack()

                fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
            }

            bottom_navigation_view.setOnNavigationItemSelectedListener {item->
                when(item.itemId)
                {
                    R.id.action_pool -> nearByplace("park")
                    R.id.action_gym -> nearByplace("gym")
                    R.id.action_pitch -> nearByplace("stadium")
                }
                true
            }

    }

    private fun nearByplace(typePlace: String) {
    //Clear all marker on Map
       mMap.clear()
           // build URL request base on location
        val url = getUrl(latitude,longitude,typePlace)

        mService.getNearbyPlaces(url)
            .enqueue(object:retrofit2.Callback<MyPlaces>{
                override fun onResponse(call: Call<MyPlaces>, response: Response<MyPlaces>) {
                    currentPlace = response!!.body()!!

                    if(response!!.isSuccessful)
                    {

                        for(i in 0 until response!!.body()!!.results!!.size)
                        {
                            val markerOptions=MarkerOptions()
                            val googlePlace = response.body()!!.results!![i]
                            val lat = googlePlace.geometry!!.location!!.lat
                            val lng = googlePlace.geometry!!.location!!.lng
                            val placeName = googlePlace.name
                            val latLng = LatLng(lat,lng)

                            markerOptions.position(latLng)
                            markerOptions.title(placeName)
                            if(typePlace.equals("park"))
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker())
                            else if(typePlace.equals("gym"))
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker())
                            else if(typePlace.equals("stadium"))
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker())
                            else
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

                            markerOptions.snippet(i.toString())
                            //add marker to map
                            mMap!!.addMarker(markerOptions)
                            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                            mMap!!.animateCamera(CameraUpdateFactory.zoomTo(1f))

                        } //move camera
                    }
                }

                override fun onFailure(call: Call<MyPlaces>, t: Throwable) {
                    Toast.makeText(baseContext,""+t!!.message,Toast.LENGTH_SHORT).show()
                }


            })

    }

    private fun getUrl(latitude: Double, longitude: Double, typePlace: String): String {

        val googlePlaceUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
        googlePlaceUrl.append("?location=$latitude,$longitude")
        googlePlaceUrl.append("&radius=10000")//10km
        googlePlaceUrl.append("&type=$typePlace")
        googlePlaceUrl.append("&key=AIzaSyAXRdqoU59S00ZXm69uBlXrFi3BtC4YaWg")

        Log.d("URL_DEBUG",googlePlaceUrl.toString())
        return googlePlaceUrl.toString()
    }

    private fun buildLocationCallBack() {
        locationCallback= object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                mLastLocation=p0!!.locations.get(p0.locations.size-1)

                if(mMarker!=null){
                    mMarker!!.remove()
                }
                latitude=mLastLocation.latitude
                longitude=mLastLocation.longitude

                val latLng=LatLng(latitude,longitude)
                val markerOptions=MarkerOptions()
                    .position(latLng)
                    .title("Your position")
                    .icon(BitmapDescriptorFactory.defaultMarker())
                mMarker=mMap.addMarker(markerOptions)

                //zmiana położenia kamery
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14f))

            }
        }

    }

    private fun buildLocationRequest() {
        locationRequest= LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=5000
        locationRequest.fastestInterval=3000
        locationRequest.smallestDisplacement=10f

    }

    private fun checkLocationsPermission():Boolean {

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISION_CODE)
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISION_CODE)
            }
            return false
        }
        else return true
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            MY_PERMISION_CODE->{
                if(grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        if(checkLocationsPermission()){
                            buildLocationRequest()
                            buildLocationCallBack()

                            fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
                            mMap.isMyLocationEnabled=true
                        }
                    }
                }else{
                    Toast.makeText(this, "Permisioned Denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mMap.isMyLocationEnabled=true
            }
        }else{
            mMap.isMyLocationEnabled=true
        }
        mMap.uiSettings.isZoomControlsEnabled

    }
}