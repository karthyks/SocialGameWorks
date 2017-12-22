package com.github.karthyks.socialgameworks.home

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.github.karthyks.socialgameworks.AppSession
import com.github.karthyks.socialgameworks.R
import com.github.karthyks.steamio.TransactionFactory
import com.github.karthyks.steamio.model.PlayerProfile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val TAG : String = "HomeActivity"
//    private var imgUser: ImageView? = null
    private var tvSteamProfileName : TextView? = null
    private var imgProfile : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        var navView = findViewById<NavigationView>(R.id.nav_view)
        tvSteamProfileName = navView.getHeaderView(0).findViewById(R.id.tvSteamName)
        imgProfile = navView.getHeaderView(0).findViewById(R.id.imgUser)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onPostResume() {
        super.onPostResume()
        var profile : PlayerProfile = GetUserInfoTask().execute(
                AppSession.getInstance(this).authenticatedUser!!.steamId).get()
        Log.d("Home Activity", profile.personaName)
        tvSteamProfileName?.text = profile.personaName
        Picasso.with(this).load(profile.avatarUrl).into(imgProfile!!)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    class GetUserInfoTask : AsyncTask<String, Void, PlayerProfile>() {
        override fun doInBackground(vararg params: String?): PlayerProfile {
            var profile : PlayerProfile = TransactionFactory.getUserInfoTransaction().execute(params[0])
                    as PlayerProfile
//            Log.d("GetSteamID32", (profile.steamId - 76561197960265728).toString())
//            TransactionFactory.getResolveProfileTransaction().execute(profile.profileUrl)
//            TransactionFactory.getPlayerInfoTransaction().execute("")
//            TransactionFactory.getMatchHistoryTransaction().execute(profile.steamId - 76561197960265728)
//            TransactionFactory.getGameInfoTransaction().execute(profile.steamId)
            TransactionFactory.getMatchInfoTransaction().execute("3633108975")
//            TransactionFactory.getLibraryTransaction().execute(profile.steamId)
            return profile
        }
    }
}
