package com.example.week3.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.week3.data.api.MovieService
import com.example.week3.model.listMovieAndInfo
import com.example.week3.model.Movie
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dataRepositories(application:Application) {
    val moviesDao = MovieFavoriteDatabase.getDatabase(application).movieDao()
    val movieAPI=MovieService.getInstance()
    val listMovieFavorite= moviesDao.getAllListMovieFatorite()
   /* fun getListMovie(): MutableList<Movie> {
        var listMovie= mutableListOf<Movie>()
        val gson=Gson();
        val listMoiveIfo=gson.fromJson<listMovieAndInfo>(DataCenter.getMovieJsonString(),listMovieAndInfo::class.java);
        if (listMoiveIfo.total_pages>0){
            listMovie=listMoiveIfo.results
        }

        return listMovie
    }*/
     fun getListMovieNowPlaying(): MutableLiveData<MutableList<Movie>>{
       var  listMovieAndInfo=MutableLiveData<MutableList<Movie>>()
        movieAPI.getApi().getNowPlaying().enqueue(object :Callback<listMovieAndInfo>{
           override fun onFailure(call: Call<listMovieAndInfo>, t: Throwable) {
               TODO("Not yet implemented")
           }

           override fun onResponse(
               call: Call<listMovieAndInfo>,
               response: Response<listMovieAndInfo>
           ) {
              response?.let {
                  listMovieAndInfo.value= it.body()?.results!!
              }
           }

       })
       return listMovieAndInfo

    }
    fun getListMovieTopRating():MutableLiveData<MutableList<Movie>>{
        var  listMovieAndInfo=MutableLiveData<MutableList<Movie>>()
        movieAPI.getApi().getTopRateMovie().enqueue(object :Callback<listMovieAndInfo>{
            override fun onFailure(call: Call<listMovieAndInfo>, t: Throwable) {
                TODO("Not yet implemented")
            }
            override fun onResponse(
                call: Call<listMovieAndInfo>,
                response: Response<listMovieAndInfo>
            ) {
                response?.let {
                    listMovieAndInfo.value= it.body()?.results!!

                }
            }

        })
        return listMovieAndInfo
    }
    suspend fun deleteMovieFavorite(movie: Movie){
        moviesDao.deleteMovie(movie)
    }
    suspend fun insertMovieFavorite(movie: Movie){
        moviesDao.insertMovie(movie)
    }
}
