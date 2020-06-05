package com.example.week3.model

import com.google.gson.annotations.SerializedName

data class listMovieAndInfo (
    @SerializedName("results") val results:MutableList<Movie>,
     val page:Int,
    @SerializedName("total_results") val total_results:Int,
    val date:String,
    @SerializedName("total_pages") val total_pages:Int
)