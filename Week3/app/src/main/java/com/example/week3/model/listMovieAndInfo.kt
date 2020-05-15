package com.example.week3.model

import android.util.JsonReader

data class listMovieAndInfo (val results:MutableList<movie>, val page:Int, val total_results:Int, val date:String, val total_pages:Int)