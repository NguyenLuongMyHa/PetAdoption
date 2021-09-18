package com.myha.petadoption.data.api

import com.myha.petadoption.data.model.CountriesItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Aria on 9/17/2021.
 */
interface BaseService {
    @GET("region/europe")
    suspend fun getCountries(): Response<List<CountriesItem>>
}