package com.example.week3.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class movie(
    val popularity:Float ,val vote_count:Int,val video:Boolean,val poster_path:String,val id:Int,
    val adult:Boolean,val backdrop_path:String,val original_language:String,val original_title:String,
    val genre_ids:Array<Int>,val title:String,val vote_average:Float,val overview:String,val release_date:String) :
    Parcelable