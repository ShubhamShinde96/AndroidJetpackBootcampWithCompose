package com.shubham.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shubham.tmdbclient.R
import com.shubham.tmdbclient.data.model.artist.Artist
import com.shubham.tmdbclient.data.model.tv_show.TvShow
import com.shubham.tmdbclient.databinding.ListItemBinding

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val tvShowList = ArrayList<TvShow>()

    fun setList(tvShow: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)

        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {

        holder.bind(tvShowList[position])

    }

    override fun getItemCount(): Int = tvShowList.size

    class TvShowViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.titleTextView.text = tvShow.name
            binding.descriptionTextView.text = tvShow.overview

            val posterUrl = "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}"

            Glide.with(binding.imageView.context)
                .load(posterUrl)
                .into(binding.imageView)
        }

    }

}