package com.myha.petadoption.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getCountries() = countryLiveData

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            val countries = repository.getCountries()
            when (countries.isSuccessful) {
                true -> {
                    with(countries.body().orEmpty()) {
                        var countryList = listOf<Country>()
                        forEach { (_, _, _, _, _, _, capital, _, _, _, _, _, _, _, name) ->
                            countryList = countryList + Country(name, capital)
                        }
                        countryLiveData.postValue(countryList)
                    }
                }
                else -> {
                    Timber.e(countries.message())
                }
            }
        }
    }
}