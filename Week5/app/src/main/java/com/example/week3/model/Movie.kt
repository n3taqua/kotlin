package com.example.week3.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_favorite")
data class Movie(
    @PrimaryKey val id:Int,
    val popularity:Float ,val vote_count:Int,val video:Boolean,val poster_path:String,
    val adult:Boolean,val backdrop_path:String,val original_language:String,val original_title:String
    ,val title:String,val vote_average:Float,val overview:String,val release_date:String
    ,var favorite:Boolean=false
    ) : Parcelable
