package com.myha.petadoption.ui.home

import com.myha.petadoption.R
import com.myha.petadoption.databinding.FragmentDiscussBinding
import com.myha.petadoption.databinding.FragmentHomeBinding
import com.myha.petadoption.databinding.FragmentLocationBinding
import com.myha.petadoption.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscussFragment : BaseFragment<FragmentDiscussBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_discuss

}