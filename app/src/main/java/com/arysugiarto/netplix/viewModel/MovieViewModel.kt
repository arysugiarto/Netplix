package com.arysugiarto.netplix.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arysugiarto.netplix.repository.Repository
import com.arysugiarto.netplix.model.Movie
import com.arysugiarto.netplix.model.TrendingMovie
import com.arysugiarto.netplix.model.Video
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {
    val MovieData=MutableLiveData<Movie>()
    val TrendingMovie=MutableLiveData<TrendingMovie>()
    val UpcomingMovie=MutableLiveData<TrendingMovie>()
    val SearchMovie=MutableLiveData<TrendingMovie>()
    val movieVideo=MutableLiveData<Video>()
    fun getMovieDetails(movieId:String){
        viewModelScope.launch {
            try {
                val movie=Repository.getMovieDetail(movieId)
                MovieData.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getTrendingMovie(media_type:String,time_window:String){
        viewModelScope.launch {
            try {
                val movie=Repository.getAllMovieData(media_type,time_window)
                TrendingMovie.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getMovieVideo(movieId: String){
        viewModelScope.launch {
            try {
                val movie=Repository.getMovieVideo(movieId)
                movieVideo.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getUpcomingMovie(){
        viewModelScope.launch {
            try {
                val movie=Repository.getUpcomingMovie()
                UpcomingMovie.postValue(movie)
            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    fun  getSearchMovie(query:String){
        viewModelScope.launch {
            try {
                val movie=Repository.getSearchMovie(query)
                SearchMovie.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}