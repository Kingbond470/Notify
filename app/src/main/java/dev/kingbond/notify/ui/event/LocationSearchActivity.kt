package dev.kingbond.notify.ui.event

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dev.kingbond.notify.R
import kotlinx.android.synthetic.main.activity_location_search.*
import java.io.IOException
import java.time.LocalTime
import java.util.*


class LocationSearchActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private var mMap: GoogleMap? = null
    internal lateinit var mLastLocation: Location
    internal var mCurrLocationMarker: Marker? = null
    internal var mGoogleApiClient: GoogleApiClient? = null
    internal lateinit var mLocationRequest: LocationRequest

    internal lateinit var loc: String
    internal lateinit var eventType: String
    internal lateinit var eventDescription: String
    internal lateinit var eventDate: String
    internal lateinit var eventTime: String
    internal lateinit var estart: String
    internal lateinit var eventTransport: String
    internal lateinit var estartLatitude: String
    internal lateinit var estartLongitude: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_search)


        loc = intent.getStringExtra("location").toString()
        eventType = intent.getStringExtra("eventType").toString()
        eventDescription = intent.getStringExtra("eventDescription").toString()
        eventDate = intent.getStringExtra("eventDate").toString()
        eventTime = intent.getStringExtra("eventTime").toString()
        eventTransport = intent.getStringExtra("eventTransport").toString()

        estart = intent.getStringExtra("start").toString()
        estartLatitude = intent.getStringExtra("startLatitude").toString()
        estartLongitude = intent.getStringExtra("startLongitude").toString()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.myMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        /*Toast.makeText(this, "lat = $estartLatitude lon = $estartLongitude", Toast.LENGTH_SHORT)
            .show()*/
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap;
       // mMap!!.mapType = GoogleMap.MAP_TYPE_HYBRID
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                buildGoogleApiClient()
                mMap!!.isMyLocationEnabled = true
            }
        } else {
            buildGoogleApiClient()
            mMap!!.isMyLocationEnabled = true
        }
    }

    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()
        mGoogleApiClient!!.connect()
    }

    override fun onLocationChanged(location: Location) {
        mLastLocation = location
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker!!.remove()
        }

        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mCurrLocationMarker = mMap!!.addMarker(markerOptions)

        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.moveCamera(CameraUpdateFactory.zoomTo(11f))

        if (mGoogleApiClient != null) {
            LocationServices.getFusedLocationProviderClient(this)
        }
    }

    override fun onConnected(p0: Bundle?) {

        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.getFusedLocationProviderClient(this)
        }
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun searchLocation(view: View) {
        val locationSearch: EditText = findViewById(R.id.et_search)
        var location: String
        location = locationSearch.text.toString().trim()
        var addressList: List<Address>? = null

        if (location == null || location == "") {
            Toast.makeText(this, "provide location", Toast.LENGTH_SHORT).show()
        } else {
            val geoCoder = Geocoder(this)
            try {
                addressList = geoCoder.getFromLocationName(location, 1)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val address = addressList!![0]

            val latLng = LatLng(address.latitude, address.longitude)
            mMap!!.addMarker(MarkerOptions().position(latLng).title(location))
            mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

            val latitude = address.latitude
            val longitude = address.longitude

            val geocoder = Geocoder(this)
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            val cityName = addresses[0].getAddressLine(0)
            val stateName = addresses[0].getAddressLine(1)
            val countryName = addresses[0].getAddressLine(2)

            val place = cityName + ", " + stateName + ", " + countryName




            btnAddLocation.setOnClickListener {

                var dis: Double = 0.0

                if (estart != "null") {
                    val lat1: Double = estartLatitude.toDouble()
                    val lon1: Double = estartLongitude.toDouble()
                    val lat2: Double = latitude
                    val lon2: Double = longitude
                    dis = distance(lat1, lon1, lat2, lon2)
                    dis = dis * 1.60934
                }


                //time

                val hh = eventTime.split(":").toTypedArray()
                val mm = hh[1].split(" ").toTypedArray()

                val localTime = LocalTime.of(hh[0].toInt(), mm[0].toInt())

                var aaa: String = mm[1].toString()


                //implement for different vehicle
                var time: Double = 0.0

                var ett = eventTime.toCharArray()
                var et: String = ett[0] + "" + ett[1]
                var ap: String = ett[6] + "" + ett[7]


                //morning
                if ((et.toInt() >= 9 && et.toInt() < 12 && ap == "AM") || (et.toInt() == 12 && ap == "PM")) {
                    if (eventTransport == "Bike")
                        time = dis / 18.0
                    else if (eventTransport == "Car")
                        time = dis / 30.0
                    else if (eventTransport == "Cab")
                        time = dis / 22.0
                    else if (eventTransport == "Walk")
                        time = dis / 3.0
                    else if (eventTransport == "Bus")
                        time = dis / 25.0
                }
                //evening
                else if (et.toInt() >= 5 && et.toInt() <= 9 && ap == "PM") {
                    if (eventTransport == "Bike")
                        time = dis / 16.0
                    else if (eventTransport == "Car")
                        time = dis / 27.0
                    else if (eventTransport == "Cab")
                        time = dis / 20.0
                    else if (eventTransport == "Walk")
                        time = dis / 2.0
                    else if (eventTransport == "Bus")
                        time = dis / 24.0
                }
                //normal
                else {
                    if (eventTransport == "Bike")
                        time = dis / 23.0
                    else if (eventTransport == "Car")
                        time = dis / 35.0
                    else if (eventTransport == "Cab")
                        time = dis / 27.0
                    else if (eventTransport == "Walk")
                        time = dis / 5.0
                    else if (eventTransport == "Bus")
                        time = dis / 47.0

                }

                //hour
                var hour: Double = 0.0
                while (time / 1.0 >= 1.0) {
                    hour = hour + 1.0
                    time = time - 1.0
                }
                time = time * 60
                //minute
                var minute: Double = 0.0
                while (time / 1.0 >= 1.0) {
                    minute = minute + 1.0
                    time = time - 1.0
                }


                val updatedTime: LocalTime =
                    localTime.minusHours(hour.toLong()).minusMinutes(minute.toLong())

                //bug is there in am pm need to fix
                /*
                val uhh = updatedTime.toString().split(":").toTypedArray()
                if (hh[0].toInt() > uhh[0].toInt()) {
                    if (aaa == "AM")
                        aaa = "PM"
                    else
                        aaa = "AM"
                }
                 */


                val alarmTime: String = updatedTime.toString() + " " + aaa

                val intent = Intent(this@LocationSearchActivity, EventActivity::class.java)
                intent.putExtra(loc, place)
                intent.putExtra(loc + "Latitude", latitude.toString())
                intent.putExtra(loc + "Longitude", longitude.toString())
                intent.putExtra("eventType", eventType)
                intent.putExtra("eventDescription", eventDescription)
                intent.putExtra("eventDate", eventDate)
                intent.putExtra("eventTime", eventTime)
                intent.putExtra("eventTransport", eventTransport)

                intent.putExtra("estart", estart)
                intent.putExtra("estartLatitude", estartLatitude)
                intent.putExtra("estartLongitude", estartLongitude)

                intent.putExtra("distance", dis.toString())

                intent.putExtra("time", alarmTime.toString())

                startActivity(intent)
            }

        }
    }

    //to find distance
    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + (Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta))))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

}