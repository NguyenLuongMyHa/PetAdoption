package com.myha.petadoption.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myha.petadoption.data.model.Adoption
import com.myha.petadoption.data.model.Country
import com.myha.petadoption.data.model.TopicAdoption
import com.myha.petadoption.databinding.ItemAdoptionSquareBinding
import com.myha.petadoption.databinding.ItemCountryBinding
import com.myha.petadoption.databinding.ItemTopicAdoptionBinding
import com.myha.petadoption.utils.view.SpacesHorizontalItemDecoration
import com.myha.petadoption.utils.view.SpacesVerticalItemDecoration
import javax.inject.Inject
import com.myha.petadoption.ui.main.MainActivity




/**
 * Created by Aria on 9/17/2021.
 */
class TopicAdoptionAdapter @Inject constructor() : RecyclerView.Adapter<TopicAdoptionAdapter.ViewHolder>() {
    var topics: List<TopicAdoption> = emptyList()
    set(value) {
        field = value
        notifyItemInserted(0)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTopicAdoptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(topics[position])

    override fun getItemCount(): Int = topics.size

    inner class ViewHolder(private val binding: ItemTopicAdoptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adoptionAdapter = AdoptionAdapter()
        fun bind(topic: TopicAdoption) {
            binding.apply {
                binding.recAdopt.apply {
                    layoutManager = LinearLayoutManager(
                        binding.root.context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    setHasFixedSize(true)
                    addItemDecoration(SpacesHorizontalItemDecoration(0, 24, 0))
                    adapter = adoptionAdapter
                }

                topic.also {
                    adoptionAdapter.adoptions = it.adoptions
                    tvAdoptionCount.text = "(${it.adoptions.size})"
                    tvTopicTitle.text = it.topicName
                    tvTopicViewAll.setOnClickListener {
                        Toast.makeText(binding.root.context, "Goto ${topic.topicName}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}