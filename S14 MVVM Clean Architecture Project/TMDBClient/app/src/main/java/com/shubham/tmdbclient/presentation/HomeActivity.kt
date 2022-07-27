package com.shubham.tmdbclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shubham.tmdbclient.R
import com.shubham.tmdbclient.databinding.ActivityHomeBinding
import com.shubham.tmdbclient.presentation.artist.ArtistActivity
import com.shubham.tmdbclient.presentation.movie.MovieActivity
import com.shubham.tmdbclient.presentation.tvshow.TvShowActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)

        binding.movieButton.setOnClickListener {

            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)

        }

        binding.artistsButton.setOnClickListener {

            val intent = Intent(this, ArtistActivity::class.java)
            startActivity(intent)

        }

        binding.tvButton.setOnClickListener {

            val intent = Intent(this, TvShowActivity::class.java)
            startActivity(intent)

        }

    }



}
















