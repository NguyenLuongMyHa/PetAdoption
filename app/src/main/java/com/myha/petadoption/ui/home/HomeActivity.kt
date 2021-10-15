package com.myha.petadoption.ui.home

import android.os.Build
import androidx.fragment.app.Fragment
import com.myha.petadoption.R
import com.myha.petadoption.databinding.ActivityHomeBinding
import com.myha.petadoption.ui.base.BaseActivity
import com.myha.petadoption.ui.base.BasePagerAdapter
import com.myha.petadoption.ui.customView.HomeBottomNavView
import com.myha.petadoption.ui.home.discuss.DiscussFragment
import com.myha.petadoption.ui.home.explore.ExploreFragment
import com.myha.petadoption.ui.home.home.HomeFragment
import com.myha.petadoption.ui.home.location.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_home

    private lateinit var homeFragment: HomeFragment
    private lateinit var locationFragment: LocationFragment
    private lateinit var exploreFragment: ExploreFragment
    private lateinit var discussFragment: DiscussFragment

    override fun init() {
        homeFragment = HomeFragment()
        locationFragment = LocationFragment()
        exploreFragment = ExploreFragment()
        discussFragment = DiscussFragment()
        val fragments =
            listOf<Fragment>(homeFragment, locationFragment, exploreFragment, discussFragment)
        val pagerAdapter = BasePagerAdapter(this, fragments)
        binding.viewPagerHome.apply {
            adapter = pagerAdapter
            isUserInputEnabled = false
        }

        binding.homeBottomNav.onTabSelected = object : HomeBottomNavView.OnTabSelected {
            override fun onTabSelected(position: Int) {
                binding.homeBottomNav.setCurrentTab(position, binding.viewPagerHome)
                setActivityTitle(position)
            }
        }
    }

    private fun setActivityTitle(position: Int) {
        when (position) {
            HomeBottomNavView.HomeBottomItem.HOME.value -> binding.tvTitle.text =
                getString(R.string.home)
            HomeBottomNavView.HomeBottomItem.LOCATION.value -> binding.tvTitle.text =
                getString(R.string.location)
            HomeBottomNavView.HomeBottomItem.EXPLORE.value -> binding.tvTitle.text =
                getString(R.string.explore)
            else -> binding.tvTitle.text = getString(R.string.discuss)
        }


    }

    override fun onBackPressed() {
        if (binding.viewPagerHome.currentItem == 0) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
                isTaskRoot && supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount ?: 0 == 0 && supportFragmentManager.backStackEntryCount == 0
            ) {
                finishAfterTransition()
            } else {
                super.onBackPressed()
            }
        } else {
            binding.viewPagerHome.currentItem = 0
        }
    }
}