package com.example.week3.view.mainview

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.example.week3.R
import com.example.week3.adapter.RvAdapter
import com.example.week3.model.Movie
import com.example.week3.view.detailview.filmDetailActivity

import com.example.week3.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_top_rating.*


class TopRatingFragment(layoutManagerSpanCount:Int=1)  : Fragment() {
    private lateinit var movieFavoriteViewModel: MovieViewModel
    var rv_layoutManager= GridLayoutManager(activity,layoutManagerSpanCount)
    private lateinit var rvAdapter_list_film:RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rating, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setContent()

    }

    private fun setContent() {
        getDataReCyclerView()
        setRecyclerView()

    }

    private fun getDataReCyclerView() {
        movieFavoriteViewModel= ViewModelProvider(this).get(MovieViewModel::class.java)
        rvAdapter_list_film= RvAdapter(rv_layoutManager)
        movieFavoriteViewModel.listMovieFavorite.observe(viewLifecycleOwner, Observer {
            rvAdapter_list_film.setListFavoriteMovie(it)
         //   rvAdapter_list_film.setListMovie(movieFavoriteViewModel.listMovieTopRating.sortedBy { it.vote_average } as MutableList<Movie>)
        })
        movieFavoriteViewModel.listMovieTopRating.observe(viewLifecycleOwner, Observer {
            rvAdapter_list_film.setListMovie(it)
        })
    }

    private fun setRecyclerView() {
        rv_list_film_top_rating.layoutManager=rv_layoutManager
        rv_list_film_top_rating.adapter=rvAdapter_list_film
        setRecyclerViewEvent()
    }

    private fun setRecyclerViewEvent() {
        rvAdapter_list_film.listener=object :RvAdapter.OnItemClickListener{
            override fun onItemClick(item: Movie) {
                val intent= Intent(activity,
                    filmDetailActivity::class.java);
                intent.putExtra("film_detail",item)
                startActivity(intent)
            }

            override fun onStartItemToggle(item: Movie, startFavorite: ToggleButton) {
                if (startFavorite.isChecked){
                    AlertDialog.Builder(activity)
                        .setTitle("My Favorite Dialog")
                        .setMessage("Bạn có muốn thêm phim này vào favorite list?")
                        .setPositiveButton("Yes"){ _, _ ->
                            item.favorite=true
                            movieFavoriteViewModel.insert(item)
                        }
                        .setNegativeButton("No"){dialog,_ ->
                            item.favorite=false
                            startFavorite.isChecked=false
                            dialog.dismiss()
                        }
                        .show()

                }
                else{
                    AlertDialog.Builder(activity)
                        .setTitle("My Favorite Dialog")
                        .setMessage("Bạn có muốn xóa phim này khỏi favorite list?")
                        .setPositiveButton("Yes"){ _, _ ->
                            item.favorite=false
                            movieFavoriteViewModel.delete(item)
                        }
                        .setNegativeButton("No"){dialog,_ ->
                            item.favorite=true
                            startFavorite.isChecked=true
                            dialog.dismiss()
                        }
                        .show()

                }

            }
        }
    }

}
