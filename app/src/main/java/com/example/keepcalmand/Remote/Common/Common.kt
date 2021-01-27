package com.example.keepcalmand.Remote.Common

import com.example.keepcalmand.Remote.IGoogleAPIService
import com.example.keepcalmand.Remote.RetrofitClient

object Common {

    private val GOOGLE_API_URL="https://maps.googleapis.com/"

    val googleAPIService:IGoogleAPIService
        get()=RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService::class.java)
}