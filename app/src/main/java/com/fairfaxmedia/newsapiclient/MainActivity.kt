package com.fairfaxmedia.newsapiclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fairfaxmedia.newsapiclient.databinding.ActivityMainBinding
import com.fairfaxmedia.newsapiclient.presentation.adapter.NewsAdapter
import com.fairfaxmedia.newsapiclient.presentation.viewmodel.NewsViewModel
import com.fairfaxmedia.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set navigation component and nav host fragment

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.news_nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvNews.setupWithNavController(
           navController
        )

        // create view model from factory method
        viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
    }
}