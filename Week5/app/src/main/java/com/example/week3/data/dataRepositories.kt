package com.example.week3.data

import android.app.Application
import com.example.week3.model.listMovieAndInfo
import com.example.week3.model.Movie
import com.google.gson.Gson

class dataRepositories(application:Application) {
    val moviesDao = MovieFavoriteDatabase.getDatabase(application).movieDao()
    val listMovieFavorite= moviesDao.getAllListMovieFatorite()
    fun getListMovie(): MutableList<Movie> {
        var listMovie= mutableListOf<Movie>()
        val gson=Gson();
        val listMoiveIfo=gson.fromJson<listMovieAndInfo>(DataCenter.getMovieJsonString(),listMovieAndInfo::class.java);
        if (listMoiveIfo.total_pages>0){
            listMovie=listMoiveIfo.results
        }

        return listMovie
    }
    suspend fun deleteMovieFavorite(movie: Movie){
        moviesDao.deleteMovie(movie)
    }
    suspend fun insertMovieFavorite(movie: Movie){
        moviesDao.insertMovie(movie)
    }
}
