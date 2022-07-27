package com.shubham.newsapiclientproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shubham.newsapiclientproject.databinding.ActivityMainBinding
import com.shubham.newsapiclientproject.presentation.adapter.NewsAdapter
import com.shubham.newsapiclientproject.presentation.viewmodel.NewsViewModel
import com.shubham.newsapiclientproject.presentation.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // For this project we're going to implement single activity, multiple fragment model, so we will construct
    // an instance of ViewModel inside this activity and share it among fragments

    lateinit var viewModel: NewsViewModel
    // Now to construct this ViewModelFactory we are going to get it using dependency injection.
    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvNews.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)


    }



}
















































































