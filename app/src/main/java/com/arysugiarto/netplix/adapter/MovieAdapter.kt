package com.arysugiarto.netplix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arysugiarto.netplix.R
import com.arysugiarto.netplix.databinding.ItemLlistBinding
import com.arysugiarto.netplix.repository.Network
import com.arysugiarto.netplix.model.Movie

class MovieAdapter(val MPosition:(String)->Unit):ListAdapter<Movie,MovieAdapter.viewholder>(HomeDiff) {

    object HomeDiff:DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }

    }

    inner class viewholder(val binding:ItemLlistBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(ItemLlistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val movie=getItem(position)
        holder.binding.apply {
            movieImg.load(Network.imageUrl+movie.poster_path){
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            tvTitle.text = movie.original_title
            movieImg.setOnClickListener {
                MPosition.invoke(movie.id.toString())
            }

        }
    }

}