package com.shubham.tmdbclient.presentation.artist

import android.opengl.Visibility
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
import com.shubham.tmdbclient.databinding.ActivityArtistBinding
import com.shubham.tmdbclient.presentation.di.Injector
import com.shubham.tmdbclient.presentation.movie.MovieAdapter
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistBinding

    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel

    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ArtistActivity, R.layout.activity_artist)

        (application as Injector).createArtistSubComponent()
            .inject(this)

        artistViewModel = ViewModelProvider(this, factory).get(ArtistViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        binding.recyclerViewArtist.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.recyclerViewArtist.adapter = adapter

        displayArtists()
    }

    private fun displayArtists() {

        binding.progressBarArtist.visibility = View.VISIBLE

        Log.i("MY_TAG", "Inside displayArtists()")

        val responseLiveData = artistViewModel.getArtists()

        responseLiveData.observe(this, Observer {

            Log.i("MY_TAG", "63, it: $it")

            if(it != null) {

                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBarArtist.visibility = View.GONE

            } else {

                binding.progressBarArtist.visibility = View.GONE
                Toast.makeText(this, "Data is not available!", Toast.LENGTH_LONG).show()
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

                updateArtists()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateArtists() {

        binding.progressBarArtist.visibility = View.VISIBLE

        val response = artistViewModel.updateArtists()

        response.observe(this, Observer {

            if(it != null) {

                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBarArtist.visibility = View.GONE

            } else {

                binding.progressBarArtist.visibility = View.GONE
                Toast.makeText(this, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }


}


























































