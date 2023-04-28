package com.example.gametronix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeScreen : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        navigationDrawer(savedInstanceState)
        bannerChange()
    }

    //Navigation Drawer
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView

    private fun navigationDrawer(savedInstanceState: Bundle?) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, NavigationDrawerMessageFragment()).commit()
//            navigationView.setCheckedItem(R.id.nav_message)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.nav_message -> supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, NavigationDrawerMessageFragment())
//                .commit()
//
//            R.id.nav_chat -> supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, NavigationDrawerChatFragment())
//                .commit()
//
//            R.id.nav_profile -> supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, NavigationDrawerProfileFragment())
//                .commit()
//
//            R.id.nav_share -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
//            R.id.nav_send -> Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }



    // Banner
    lateinit var banner: ImageView

    private fun bannerChange() {
        banner = findViewById(R.id.banner)
        val bannerArray = intArrayOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3,
            R.drawable.banner4, R.drawable.banner5)
        var i = 0

        val handler: Handler = Handler()
        val run = object : Runnable {
            override fun run() {
                banner.setImageResource(bannerArray[i])
                i++
                if (i > bannerArray.size-1)
                    i = 0
                handler.postDelayed(this, 4000)
            }
        }
        handler.post(run)
    }
}