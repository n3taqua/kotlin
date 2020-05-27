package com.example.week3.model

data class listMovieAndInfo (val results:MutableList<Movie>, val page:Int, val total_results:Int, val date:String, val total_pages:Int)