package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEvent();
    }

    private fun setEvent() {

        btn_Lets_start.setOnClickListener{
            val intent=Intent(this,Connexion::class.java)
            startActivity(intent)
        }
    }
}
