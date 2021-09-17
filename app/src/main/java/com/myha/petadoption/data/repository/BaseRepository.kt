package com.myha.petadoption.data.repository

import com.myha.petadoption.data.api.BaseService

/**
 * Created by Aria on 9/17/2021.
 */
class BaseRepository(private val apiService: BaseService) {
    suspend fun getCountries() = apiService.getCountries()
}