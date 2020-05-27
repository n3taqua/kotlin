
package com.example.week3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.week3.dao.MovieDao
import com.example.week3.model.Movie

@Database(entities = [Movie::class], version = 2)
abstract class MovieFavoriteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    companion object {
        @Volatile
        private var INSTANCE: MovieFavoriteDatabase? = null
        private val DB_NAME="movie_db"
        fun getDatabase(context: Context): MovieFavoriteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieFavoriteDatabase::class.java,
                    DB_NAME
                ) .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
