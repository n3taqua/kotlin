package com.example.week3.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.week3.model.Movie

@Dao
interface MovieDao {
    @Query("select * from movie_favorite")
    fun getAllListMovieFatorite():LiveData<MutableList<Movie>>

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: Movie)

    @Update(onConflict = REPLACE)
    fun updateMovie(movie: Movie)
    @Delete()
    fun deleteMovie(movie: Movie)
}
