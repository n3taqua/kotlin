package com.example.week3.adapter

 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
 import android.widget.TextView
 import android.widget.ToggleButton
 import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.Movie
 import com.example.week3.util.Image_BaseURL
 import com.example.week3.util.SPAN_COUNT_ONE
 import com.example.week3.util.VIEW_TYPE_GRID
 import com.example.week3.util.VIEW_TYPE_LIST


class RvAdapter(var layoutMananger:GridLayoutManager) :RecyclerView.Adapter<RvAdapter.MovieViewHolder>() {


    private  var listMovie= mutableListOf<Movie>()
    private var listFavoriteMovie= mutableListOf<Movie>()

    var listener: OnItemClickListener? =null
    fun setListFavoriteMovie(listFavoriteMovie:MutableList<Movie>){
        this.listFavoriteMovie=listFavoriteMovie
        notifyDataSetChanged()
    }

    fun setListMovie(listMovie: MutableList<Movie>){
       listMovie.forEach {
           listFavoriteMovie.forEach {item->
               if (item.id==it.id)
                   it.favorite=true
           }
       }
        this.listMovie=listMovie
        notifyDataSetChanged()
    }
    interface OnItemClickListener {
        fun onItemClick(item: Movie)
        fun onStartItemToggle(
            item: Movie,
            startFavorite: ToggleButton
        )
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
        val start_favorite=
            if(viewType==1) item_view.findViewById<ToggleButton>(R.id.btn_like_film_list)
            else item_view.findViewById(R.id.btn_like_film_grid)

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
        Glide.with(holder.img_film_poster).load(Image_BaseURL+listMovie[position].poster_path).into(holder.img_film_poster)
        holder.txt_title.text=listMovie[position].title
        if(getItemViewType(position)==VIEW_TYPE_LIST){
            holder.txt_description!!.text=listMovie[position].overview
        }
        holder.start_favorite?.isChecked  =listMovie[position].favorite
        holder.start_favorite?.setOnClickListener(){
            listener?.onStartItemToggle(listMovie[position],holder.start_favorite)
        }
        holder.itemView.setOnClickListener {
            listener?.onItemClick(listMovie[position])
        }
    }


}