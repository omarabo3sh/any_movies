package com.example.myapplication.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.MovieResult
import com.example.myapplication.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.Holder>() {

    var movieList: ArrayList<MovieResult>? = null

    var onItemClick: OnItemClick? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {

        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        movieList?.let {
            holder.bind(it[position])
        }

    }

    override fun getItemCount(): Int {
        return if (movieList == null) 0 else movieList!!.size

    }


    inner class Holder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                movieList?.get(layoutPosition)?.id?.let { it1 -> onItemClick?.onClick(it1) }
            }

        }

        fun bind(movieResult: MovieResult) {
            binding.apply {
                textName.text = movieResult.original_title
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500"+movieResult.poster_path).into(imageMovie)


            }

        }
    }

    interface OnItemClick {
        fun onClick(id : Int)

    }
}