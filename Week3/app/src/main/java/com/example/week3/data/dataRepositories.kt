package com.example.week3.data

import com.example.week3.model.listMovieAndInfo
import com.example.week3.model.movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khtn.androidcamp.DataCenter

class dataRepositories {
    fun getListMovie():MutableList<movie>{
        var listMovie= mutableListOf<movie>()
        val gson=Gson();
        val listMoiveIfo=gson.fromJson<listMovieAndInfo>(DataCenter.getMovieJsonString(),listMovieAndInfo::class.java);
        if (listMoiveIfo.total_pages>0){
            listMovie=listMoiveIfo.results
        }
        return listMovie
    }
}