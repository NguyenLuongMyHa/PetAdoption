package com.myha.petadoption.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myha.petadoption.data.api.ResultWrapper
import com.myha.petadoption.data.model.Countries
import com.myha.petadoption.data.model.Country
import com.myha.petadoption.data.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Aria on 9/17/2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: BaseRepository) : ViewModel() {
    private val countryLiveData = MutableLiveData<List<Country>>()
    val loading = MutableLiveData<Boolean>()

    fun getCountries() = countryLiveData

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            loading.postValue(true)
            val resultWrapper = repository.getCountries()
            when (resultWrapper) {
                is ResultWrapper.Success<*> -> {
                    val countriesResponse = resultWrapper.value as Countries
                    var countryList = listOf<Country>()
                    with(countriesResponse) {
                        forEach { (_, _, _, _, _, _, capital, _, _, _, _, _, _, _, name) ->
                            countryList = countryList + Country(name, capital)
                        }
                        countryLiveData.postValue(countryList)
                    }
                    loading.postValue(false)
                }
                is ResultWrapper.NetworkError -> {
                    Timber.e("Network Error")
                    loading.postValue(false)
                }
                else -> {
                    Timber.e("Error")
                    loading.postValue(false)
                }
            }
        }
    }
}