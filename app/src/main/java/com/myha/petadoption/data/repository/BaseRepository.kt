package com.myha.petadoption.data.repository

import com.myha.petadoption.data.api.ApiWrapper
import com.myha.petadoption.data.api.BaseService
import com.myha.petadoption.data.api.SafeApi
import com.myha.petadoption.data.model.CountriesItem

/**
 * Created by Aria on 9/17/2021.
 */
class BaseRepository(private val apiService: BaseService) : SafeApi() {
    suspend fun getCountries(): ApiWrapper<List<CountriesItem>> =
        safeApi { apiService.getCountries() }
}