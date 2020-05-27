package com.example.week3.view.detailview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.Movie
import kotlinx.android.synthetic.main.activity_film_detail.*

class filmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
       setContent();
    }
 private fun setContent() {
        val movie=intent.getParcelableExtra<Movie>("film_detail")
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+movie.backdrop_path).into(img_film_back)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+movie.poster_path).into(img_film_avatar)
        txt_film_title_detail.text=movie.title
        txt_film_detail_overview.text=movie.overview
        rat_film_detail.numStars=5
        rat_film_detail.rating= 5*movie.vote_average/10
    }
}
