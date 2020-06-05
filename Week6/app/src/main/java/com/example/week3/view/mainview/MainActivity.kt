package com.example.week3.view.mainview



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.week3.R
import com.example.week3.util.VIEW_TYPE_GRID
import com.example.week3.util.VIEW_TYPE_LIST

import com.example.week3.viewmodel.MovieViewModel
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var movieFavoriteViewModel: MovieViewModel
    var FRAGMENT_STATUS="NOW_PLAYING";
    var layoutManagerSpanCount=1;
    var selectedFragment :Fragment=
        NowPlayingFragment(layoutManagerSpanCount)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this);
        setContent()
    }


    private fun setContent() {
        setSupportActionBar(findViewById(R.id.tol_bar_main))
        setFragmentPreviousOpen()
        setBottomNavListenner()


    }

    private fun setBottomNavListenner() {
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_now_playing -> {
                    selectedFragment=
                        NowPlayingFragment(
                            layoutManagerSpanCount
                        )
                    FRAGMENT_STATUS="NOW_PLAYING";

                }
                R.id.nav_top_ratting -> {
                    selectedFragment=
                        TopRatingFragment(
                            layoutManagerSpanCount
                        )
                    FRAGMENT_STATUS="TOP_RATING";

                }
                R.id.nav_my_favorite -> {
                    selectedFragment=
                        MyFavoriteFragment(
                            layoutManagerSpanCount
                        )
                    FRAGMENT_STATUS="MY_FAVORITE";
                }
            }

            startFragment()
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setFragmentPreviousOpen() {
        val sharedPreferences = getSharedPreferences("PREFERCENCE_PRAGMENT", MODE_PRIVATE)
        FRAGMENT_STATUS= sharedPreferences.getString("PREFERCENCE_PRAGMENT_STATUS","NOW_PLAYING").toString()
        prepareFragment()
        startFragment()
    }

    private fun prepareFragment() {
        when(FRAGMENT_STATUS){
            "NOW_PLAYING"-> {
                bottom_nav.selectedItemId= R.id.nav_now_playing
                selectedFragment=
                    NowPlayingFragment(
                        layoutManagerSpanCount
                    )
            }
            "TOP_RATING"->{
                bottom_nav.selectedItemId= R.id.nav_top_ratting
                selectedFragment=
                    TopRatingFragment(
                        layoutManagerSpanCount
                    )
            }
            "MY_FAVORITE"->{
                bottom_nav.selectedItemId= R.id.nav_my_favorite
                selectedFragment=
                    MyFavoriteFragment(
                        layoutManagerSpanCount
                    )
            }

        }
    }

    private fun startFragment() {
        toolbar_title.text=when(FRAGMENT_STATUS){
            "NOW_PLAYING"->"Now Playing"
            "TOP_RATING"->"Top Rating"
            "MY_FAVORITE"->"My Favorite"
            else -> "Now Playing"
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_main, selectedFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences("PREFERCENCE_PRAGMENT", MODE_PRIVATE)
        sharedPreferences.edit().putString("PREFERCENCE_PRAGMENT_STATUS",FRAGMENT_STATUS).apply()
    }
    override fun onDestroy() {
        super.onDestroy()



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        switchMenu(item)
        return super.onOptionsItemSelected(item)
    }

    private fun switchMenu(item: MenuItem) {
        if (layoutManagerSpanCount== VIEW_TYPE_LIST) {
            item.icon=getDrawable(R.drawable.ic_action_main_listview_grid)
            layoutManagerSpanCount=VIEW_TYPE_GRID

        } else {
            item.icon=getDrawable(R.drawable.ic_action_main_listview_list)
            layoutManagerSpanCount=VIEW_TYPE_LIST
        }
      prepareFragment()
      startFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main,menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
