package com.example.week3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.movie
import kotlinx.android.synthetic.main.item_list_movie_list_type.view.*


class rv_adapter(var listMovie:List<movie>,var context:Context,var layoutMananger:GridLayoutManager) :RecyclerView.Adapter<rv_adapter.MovieViewHolder>() {

    val SPAN_COUNT_ONE = 1
    val SPAN_COUNT_THREE = 3
    val VIEW_TYPE_LIST = 1
    val VIEW_TYPE_GRID = 2


    var listener: OnItemClickListener? =null
    interface OnItemClickListener {
        fun onItemClick(item: movie)
    }
    class MovieViewHolder(item_view:View,viewType: Int): RecyclerView.ViewHolder(item_view){
        val txt_title:TextView =
            if (viewType==1) item_view.findViewById(R.id.txt_title)
            else item_view.findViewById(R.id.txt_file_title_grid)
        val img_film_poster:ImageView=
            if (viewType==1) item_view.findViewById(R.id.img_film_poster)
            else item_view.findViewById(R.id.img_film_poster_grid)
        val txt_description=
            if(viewType==1) item_view.findViewById<TextView>(R.id.txt_description)
            else null


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

       return MovieViewHolder(
           if(viewType==VIEW_TYPE_LIST) {
               LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_list_type,parent,false)
           } else
               LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_list_grid_type,parent,false)
       ,viewType
       )
    }

    override fun getItemCount(): Int {
      return listMovie.size
    }

    override fun getItemViewType(position: Int): Int {
        val spanCount: Int = layoutMananger.spanCount
        return if (spanCount == SPAN_COUNT_ONE) {
            VIEW_TYPE_LIST
        } else {
            VIEW_TYPE_GRID
        }

    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+listMovie[position].poster_path).into(holder.img_film_poster)
        holder.txt_title.text=listMovie[position].title
        if(getItemViewType(position)==VIEW_TYPE_LIST){
            holder.txt_description!!.text=listMovie[position].overview
        }
        holder.itemView.setOnClickListener {
            listener?.onItemClick(listMovie[position])
        }
    }


}