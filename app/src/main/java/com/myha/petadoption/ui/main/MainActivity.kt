package com.myha.petadoption.ui.main

import com.myha.petadoption.R
import com.myha.petadoption.databinding.ActivityMainBinding
import com.myha.petadoption.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
	override val layoutRes: Int
		get() = R.layout.activity_main

	override fun init() {
		binding.avt1.setOnClickListener {
			Timber.d("avt1")
		}
		binding.avt2.setOnClickListener {
			Timber.d("avt2")
		}
		binding.avt3.setOnClickListener {
			Timber.d("avt3")
		}
	}
}