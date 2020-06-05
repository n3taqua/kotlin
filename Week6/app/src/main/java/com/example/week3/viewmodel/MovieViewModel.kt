package com.example.week3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.week3.data.dataRepositories
import com.example.week3.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application):AndroidViewModel(application){
    private val repository: dataRepositories = dataRepositories(application)

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
 //   val listMovieFavorite: LiveData<List<movie>>
     val listMovieNowPlaying:MutableLiveData<MutableList<Movie>>
     val listMovieTopRating:MutableLiveData<MutableList<Movie>>
     val listMovieFavorite:LiveData<MutableList<Movie>>
    init {
        listMovieFavorite = repository.listMovieFavorite
        //listAllMovie=repository.getListMovie()
        listMovieTopRating=repository.getListMovieTopRating()
        listMovieNowPlaying=repository.getListMovieNowPlaying()

    }
    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMovieFavorite(movie)
    }
    fun delete(movie: Movie) =viewModelScope.launch  (Dispatchers.IO){
        repository.deleteMovieFavorite(movie)
    }

}
