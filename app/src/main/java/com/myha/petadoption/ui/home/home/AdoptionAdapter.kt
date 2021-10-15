package com.myha.petadoption.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myha.petadoption.data.model.Adoption
import com.myha.petadoption.data.model.Country
import com.myha.petadoption.databinding.ItemAdoptionSquareBinding
import com.myha.petadoption.databinding.ItemCountryBinding
import javax.inject.Inject

/**
 * Created by Aria on 9/17/2021.
 */
class AdoptionAdapter @Inject constructor() : RecyclerView.Adapter<AdoptionAdapter.ViewHolder>() {
    var adoptions: List<Adoption> = emptyList()
    set(value) {
        field = value
        notifyItemInserted(0)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemAdoptionSquareBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(adoptions[position])

    override fun getItemCount(): Int = adoptions.size

    inner class ViewHolder(private val binding: ItemAdoptionSquareBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(adoption: Adoption) {
            binding.apply {
                adoption.also {
                    tvAdoptionFee.text = it.fee.toString()
                    tvPetLocation.text = it.pet.location
                    tvPetName.text = it.pet.name
                }
            }
        }
    }
}