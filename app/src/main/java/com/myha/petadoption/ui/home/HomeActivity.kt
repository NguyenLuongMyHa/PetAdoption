package com.myha.petadoption.ui.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.myha.petadoption.R
import com.myha.petadoption.databinding.ActivityHomeBinding
import com.myha.petadoption.ui.base.BaseActivity
import com.myha.petadoption.ui.customView.HomeBottomNavView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_home

    override fun init() {
        binding.homeBottomNav.onTabSelected = object : HomeBottomNavView.OnTabSelected {
            override fun onTabSelected(position: Int) {
                val navController = Navigation.findNavController(this@HomeActivity, R.id.nav_home)
                when (position) {
                    HomeBottomNavView.HomeBottomItem.HOME.value -> if (!navController.popBackStack())
                        navController.navigate(R.id.homeFragment)
                    HomeBottomNavView.HomeBottomItem.LOCATION.value -> if (!navController.popBackStack())
                        navController.navigate(R.id.locationFragment)
                    HomeBottomNavView.HomeBottomItem.EXPLORE.value -> if (!navController.popBackStack())
                        navController.navigate(R.id.exploreFragment)
                    HomeBottomNavView.HomeBottomItem.DISCUSS.value -> if (!navController.popBackStack())
                        navController.navigate(R.id.discussFragment)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
            isTaskRoot && supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount ?: 0 == 0 && supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}