package com.example.week3.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_favorite")
data class Movie(
    @PrimaryKey val id:Int,
    val popularity:Float,
    @SerializedName("vote_count")val vote_count:Int,
    val video:Boolean,
    @SerializedName("poster_path")  val poster_path:String,
    val adult:Boolean,
    @SerializedName("backdrop_path")  val backdrop_path:String,
    @SerializedName("original_language") val original_language:String,
    @SerializedName("original_title") val original_title:String,
    val title:String,
    @SerializedName("vote_average") val vote_average:Float,
    val overview:String,
    @SerializedName("release_date") val release_date:String,
    var favorite:Boolean=false
    ) : Parcelable
