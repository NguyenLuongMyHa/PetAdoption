package com.myha.petadoption.data.api

import com.myha.petadoption.data.model.Countries
import retrofit2.http.GET

/**
 * Created by Aria on 9/17/2021.
 */
interface BaseService {
    @GET("region/europe")
    suspend fun getCountries(): Countries
}