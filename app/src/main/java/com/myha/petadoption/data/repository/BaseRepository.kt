package com.myha.petadoption.data.repository

import com.myha.petadoption.data.api.BaseService
import com.myha.petadoption.data.api.ResultWrapper
import com.myha.petadoption.data.api.safeApi
import kotlinx.coroutines.Dispatchers.IO

/**
 * Created by Aria on 9/17/2021.
 */
class BaseRepository(private val apiService: BaseService) {
    suspend fun getCountries(): ResultWrapper = safeApi(IO) { apiService.getCountries() }

}