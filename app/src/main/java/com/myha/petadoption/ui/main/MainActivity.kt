package com.myha.petadoption.ui.main

import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myha.petadoption.R
import com.myha.petadoption.databinding.ActivityMainBinding
import com.myha.petadoption.ui.base.BaseActivity
import com.myha.petadoption.utils.extension.ViewExtension.hide
import com.myha.petadoption.utils.extension.ViewExtension.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
	override val layoutRes: Int
		get() = R.layout.activity_main

	private val mainViewModel: MainViewModel by viewModels()

	override fun init() {
		setUpUI()
		setUpObservers()
	}

	private fun setUpUI() {
		binding.countryRecyclerview.apply {
			layoutManager = LinearLayoutManager(this@MainActivity)
			setHasFixedSize(true)
			adapter = CountryAdapter()
			addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
		}
	}

	private fun setUpObservers() {
		mainViewModel.loading.observe(this, {
			if (it) binding.progressCircular.show()
			else binding.progressCircular.hide()
		})

		mainViewModel.getCountries().observe(this, { countryList ->
			countryList?.let {
				binding.countryRecyclerview.apply {
					with(adapter as CountryAdapter) {
						countries = it
						notifyDataSetChanged()
					}
				}
			}
		})
	}
}