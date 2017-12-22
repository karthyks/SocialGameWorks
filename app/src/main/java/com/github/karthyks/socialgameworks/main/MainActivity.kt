package com.github.karthyks.socialgameworks.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.github.karthyks.socialgameworks.AppSession
import com.github.karthyks.socialgameworks.R


class MainActivity : AppCompatActivity() {

    private var tvUserId: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvUserId = findViewById(R.id.tv_user_id)
        tvUserId!!.text = AppSession.getInstance(this).authenticatedUser!!.steamId
    }


}
