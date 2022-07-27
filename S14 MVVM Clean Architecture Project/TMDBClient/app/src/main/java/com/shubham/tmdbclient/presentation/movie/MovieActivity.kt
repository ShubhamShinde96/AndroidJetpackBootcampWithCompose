package com.shubham.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.tmdbclient.R
import com.shubham.tmdbclient.databinding.ActivityMovieBinding
import com.shubham.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel

    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MovieActivity, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent()
            .inject(this) // after this now we can inject dependencies to this activity, lets inject movie viewModel factory

        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)


        initRecyclerView()
    }

    private fun initRecyclerView() {

        binding.recyclerViewMovie.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.recyclerViewMovie.adapter = adapter

        displayPopularMovies()
    }

    private fun displayPopularMovies() {

        binding.progressBarMovie.visibility = View.VISIBLE

        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this@MovieActivity, Observer {

            if(it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBarMovie.visibility = View.GONE
            } else {
                binding.progressBarMovie.visibility = View.GONE
                Toast.makeText(this@MovieActivity, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.update, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovies() {
        binding.progressBarMovie.visibility = View.VISIBLE
        val response = movieViewModel.updateMovieList()
        response.observe(this@MovieActivity, Observer {
            if(it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBarMovie.visibility = View.GONE
            } else {
                binding.progressBarMovie.visibility = View.GONE
                Toast.makeText(this@MovieActivity, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }

}