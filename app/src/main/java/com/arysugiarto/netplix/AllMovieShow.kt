package com.arysugiarto.netplix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.arysugiarto.netplix.repository.Network
import com.arysugiarto.netplix.viewModel.MovieViewModel
import com.arysugiarto.netplix.adapter.MovieAdapter
import com.arysugiarto.netplix.databinding.FragmentAllMovieShowBinding

class AllMovieShow : Fragment() {

    private val binding: FragmentAllMovieShowBinding by lazy {
        FragmentAllMovieShowBinding.inflate(layoutInflater)
    }
    private val homeViewModelProvider: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }
    private val movAdapter by lazy {
        MovieAdapter { movie ->
            val action = AllMovieShowDirections.actionAllMovieShowToMovieDetailShow(movie)
            findNavController().navigate(action)
        }
    }
    private val movAdapter2 by lazy {
        MovieAdapter { movie ->
            val action = AllMovieShowDirections.actionAllMovieShowToMovieDetailShow(movie)
            findNavController().navigate(action)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observe()
        binding.rcTrending.adapter = movAdapter
        binding.rcPopular.adapter = movAdapter2
        homeViewModelProvider.getTrendingMovie(Trending.Media_type_all, Trending.Time_Zone_Week)
        homeViewModelProvider.getUpcomingMovie()

        return binding.root
    }

    private fun observe() {
        binding.progressbar2.visibility = View.VISIBLE
        homeViewModelProvider.TrendingMovie.observe(viewLifecycleOwner) {
            binding.progressbar2.visibility = View.GONE
            movAdapter.submitList(it.results)
            val imageList = mutableListOf<SlideModel>()
            it.results.forEach { movie ->
                imageList.add(SlideModel(Network.imageUrl + movie.poster_path, ScaleTypes.FIT))
            }
            binding.imageSlider.startSliding(300)
            binding.imageSlider.setImageList(imageList)
            binding.imageSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    val id = it.results[position].id
                    val action =
                        AllMovieShowDirections.actionAllMovieShowToMovieDetailShow(id.toString())
                    findNavController().navigate(action)
                }
            })

        }
        homeViewModelProvider.UpcomingMovie.observe(viewLifecycleOwner) {
            movAdapter2.submitList(it.results)
        }

    }

}