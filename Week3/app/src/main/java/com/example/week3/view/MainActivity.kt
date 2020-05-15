package com.example.week3.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.R
import com.example.week3.adapter.rv_adapter
import com.example.week3.data.dataRepositories
import com.example.week3.model.movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listMovieRepositories=dataRepositories()
    var listMovie =listMovieRepositories.getListMovie()
    var rv_layoutManager=GridLayoutManager(this,1)
    var rv_adapter_list_film=rv_adapter(listMovie,this,rv_layoutManager);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent()
    }

    private fun setContent() {
        setSupportActionBar(findViewById(R.id.tol_bar_main))
        setRecyclerView()
    }

    private fun setRecyclerView() {
        rv_list_film.layoutManager=rv_layoutManager
        rv_list_film.adapter=rv_adapter_list_film
        rv_adapter_list_film.listener=object :rv_adapter.OnItemClickListener{
            override fun onItemClick(item: movie) {
                val intent=Intent(this@MainActivity,filmDetailActivity::class.java);
                intent.putExtra("film_detail",item)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        switchLayout()
        switchMenu(item)
        return super.onOptionsItemSelected(item)
    }

    private fun switchLayout() {
        if (rv_layoutManager.spanCount  == 1) {
            rv_layoutManager.spanCount =3;
        } else {
            rv_layoutManager.spanCount=1
        }
     //   rv_list_film.layoutManager=rv_layoutManager
        rv_adapter_list_film.layoutMananger=rv_layoutManager
    }

    private fun switchMenu(item: MenuItem) {
        if (rv_layoutManager.spanCount == 1) {
            item.icon=getDrawable(R.drawable.ic_action_main_listview_list)
        } else {
            item.icon=getDrawable(R.drawable.ic_action_main_listview_grid)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main,menu)
        return super.onCreateOptionsMenu(menu)
    }
}
