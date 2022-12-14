package com.arysugiarto.netplix.apiservices

import com.arysugiarto.netplix.model.Movie
import com.arysugiarto.netplix.model.TrendingMovie
import com.arysugiarto.netplix.model.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id")movieId:String,
        @Query("api_key")apikey:String
    ):Movie
    @GET("/3/trending/{media_type}/{time_window}")
    suspend fun getAllTrendingMovie(
        @Path("media_type")mediaType:String,
        @Path("time_window")time_window:String,
        @Query("api_key")apikey:String
    ):TrendingMovie

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieVedio(
        @Path("movie_id")movie_id:String,
        @Query("api_key")apikey: String
    ):Video

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") api_key:String
    ):TrendingMovie


    @GET("/3/search/movie")
    suspend fun getSearchMovie(
        @Query("api_key") api_key: String,
        @Query("query") Query:String
    ):TrendingMovie
}