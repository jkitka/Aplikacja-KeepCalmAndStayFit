package com.example.keepcalmand.Remote

//import android.telecom.Call
import com.example.keepcalmand.Model.MyPlaces
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleAPIService {

    @GET
    fun getNearbyPlaces(@Url url:String): Call<MyPlaces>
}