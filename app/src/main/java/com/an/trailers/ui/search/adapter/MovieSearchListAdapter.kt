package com.an.trailers.ui.search.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import com.an.trailers.R
import com.an.trailers.data.local.entity.MovieEntity
import com.an.trailers.databinding.MoviesListItemBinding
import com.squareup.picasso.Picasso

import java.util.ArrayList

class MovieSearchListAdapter(private val activity: Activity) :
    RecyclerView.Adapter<MovieSearchListAdapter.CustomViewHolder>() {

    private var trailers: List<MovieEntity>

    var items: List<MovieEntity>
        get() = trailers
        set(trailers) {
            this.trailers = trailers
            notifyDataSetChanged()
        }

    init {
        this.trailers = ArrayList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemBinding = MoviesListItemBinding.inflate(layoutInflater, viewGroup, false)
        val viewHolder = CustomViewHolder(itemBinding)
        return viewHolder
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, i: Int) {
        val trailer = getItem(i)
        customViewHolder.bindTo(trailer)
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    fun getItem(position: Int): MovieEntity {
        return trailers[position]
    }

    inner class CustomViewHolder(private val binding: MoviesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels

            itemView.layoutParams = RecyclerView.LayoutParams(
                (width * 0.85f).toInt(),
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        fun bindTo(trailer: MovieEntity) {
            Picasso.get().load(trailer.getFormattedPosterPath())
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.image)
        }
    }
}
