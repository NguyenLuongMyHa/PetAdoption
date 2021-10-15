package com.myha.petadoption.ui.home.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.myha.petadoption.R
import com.myha.petadoption.databinding.FragmentHomeBinding
import com.myha.petadoption.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        val homeTopFragment = HomeTopFragment()
        addFragment(homeTopFragment, R.id.fragmentHomeTop)
        val homePagerFragment = HomePagerFragment()
        addFragment(homePagerFragment, R.id.fragmentHomePager)
    }

    private fun addFragment(fragment: Fragment, viewResId: Int) {
        val fm = childFragmentManager
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(viewResId, fragment)
        fragmentTransaction.commit()
    }
}