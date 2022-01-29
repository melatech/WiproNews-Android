package com.melatech.wipronews.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.melatech.wipronews.R
import com.melatech.wipronews.databinding.ActivityMainBinding
import com.melatech.wipronews.presentation.viewmodel.NewsViewModel
import com.melatech.wipronews.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //use di to inject ViewModelFactory
    @Inject
    lateinit var factory: NewsViewModelFactory
    //shared ViewModel
    lateinit var viewModel: NewsViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, factory)
            .get(NewsViewModel::class.java)
    }
}