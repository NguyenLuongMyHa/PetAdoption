package com.myha.petadoption.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myha.petadoption.data.model.Country
import com.myha.petadoption.databinding.ItemCountryBinding
import javax.inject.Inject

/**
 * Created by Aria on 9/17/2021.
 */
class CountryAdapter @Inject constructor() : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    var countries: List<Country> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(countries[position])

    override fun getItemCount(): Int = countries.size

    inner class ViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                country.also { (name, capital) ->
                    nameTextview.text = name
                    capitalTextview.text = capital
                }
            }
        }
    }
}