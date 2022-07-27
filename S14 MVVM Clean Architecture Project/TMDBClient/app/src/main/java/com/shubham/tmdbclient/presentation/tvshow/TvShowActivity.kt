package com.shubham.tmdbclient.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.shubham.tmdbclient.databinding.ActivityTvShowBinding
import com.shubham.tmdbclient.db.TvShowDao
import com.shubham.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding

    @Inject
    lateinit var factory: TvShowViewModelFactory

    private lateinit var tvShowViewModel: TvShowViewModel

    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@TvShowActivity, R.layout.activity_tv_show)

        (application as Injector).createTvShowSubComponent()
            .inject(this)

        tvShowViewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)

        initRecyclerView()

    }

    private fun initRecyclerView() {

        binding.recyclerViewTvShow.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter()
        binding.recyclerViewTvShow.adapter = adapter

        displayTvShows()
    }

    private fun displayTvShows() {

        binding.progressBarTvShow.visibility = View.VISIBLE

        val responseLiveData = tvShowViewModel.getTvShow()

        responseLiveData.observe(this, Observer {

            if(it != null) {

                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBarTvShow.visibility = View.GONE

            } else {

                binding.progressBarTvShow.visibility = View.GONE
                Toast.makeText(this, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater : MenuInflater = menuInflater

        inflater.inflate(R.menu.update, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {

            R.id.action_update -> {

                updateTvShowList()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateTvShowList() {

        binding.progressBarTvShow.visibility = View.VISIBLE

        val responseLiveData = tvShowViewModel.updateTvShow()

        responseLiveData.observe(this, Observer {

            if(it != null) {

                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBarTvShow.visibility = View.GONE

            } else {

                binding.progressBarTvShow.visibility = View.GONE
                Toast.makeText(this, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }

}

















