package com.myha.petadoption.ui.home.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myha.petadoption.R
import com.myha.petadoption.data.model.Adoption
import com.myha.petadoption.data.model.Pet
import com.myha.petadoption.data.model.TopicAdoption
import com.myha.petadoption.databinding.FragmentHomeTopBinding
import com.myha.petadoption.ui.base.BaseFragment
import com.myha.petadoption.ui.main.CountryAdapter
import com.myha.petadoption.utils.view.SpacesVerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max
import kotlin.math.min

@AndroidEntryPoint
class HomeTopFragment : BaseFragment<FragmentHomeTopBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home_top

    private val topicAdoptionAdapter = TopicAdoptionAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        var mScrollY = 0F
        binding.recAdopt.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rcv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rcv, dx, dy)
                mScrollY += dy.toFloat()
                mScrollY = max(mScrollY, 0F)
                binding.layoutHeader.translationY = min(-mScrollY, 0F)
            }
        })
        val adoption1 = Adoption("1", 0, Pet("1", "Bo lì", "Bình Chánh, TPHCM"))
        val adoption2 = Adoption("1", 0, Pet("1", "Bo lì 2", "Bình Chánh, TPHCM"))
        val adoption3 = Adoption("1", 0, Pet("1", "Bo lì 3", "Bình Chánh, TPHCM"))
        val adoption4 = Adoption("1", 0, Pet("1", "Bo lì 4", "Bình Chánh, TPHCM"))
        val adoption5 = Adoption("1", 0, Pet("1", "Bo lì 5", "Bình Chánh, TPHCM"))
        val adoption6 = Adoption("1", 0, Pet("1", "Bo lì 6", "Bình Chánh, TPHCM"))
        val adoption7 = Adoption("1", 0, Pet("1", "Bo lì 7", "Bình Chánh, TPHCM"))
        val adoptions1 = listOf(adoption1,adoption2, adoption3, adoption4, adoption5, adoption6, adoption7)
        val adoptions2 = listOf(adoption2, adoption4, adoption6, adoption7)
        val adoptions3 = listOf(adoption3, adoption5, adoption6, adoption7)
        val adoptions4 = listOf(adoption1,adoption2, adoption4)

        val topic1 = TopicAdoption("Urgent", adoptions1)
        val topic2 = TopicAdoption("New Pet", adoptions2)
        val topic3 = TopicAdoption("Near by (2km)", adoptions3)
        val topic4 = TopicAdoption("Lost", adoptions4)
        val topics = listOf(topic1, topic2, topic3, topic4)

        binding.recAdopt.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = topicAdoptionAdapter
            addItemDecoration(SpacesVerticalItemDecoration(24, 0, 32))
            topicAdoptionAdapter.topics = topics
        }
    }


}