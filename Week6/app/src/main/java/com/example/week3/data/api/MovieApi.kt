package com.example.week3.data.api

import com.example.week3.model.Movie
import com.example.week3.model.listMovieAndInfo
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/top_rated")
    fun getTopRateMovie():Call<listMovieAndInfo>
    @GET("movie/now_playing")
    fun getNowPlaying():Call<listMovieAndInfo>
}