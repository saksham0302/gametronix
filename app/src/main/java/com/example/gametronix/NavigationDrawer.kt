package com.example.gametronix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.gametronix.fragments.*
import com.google.android.material.navigation.NavigationView

class NavigationDrawer : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    //Navigation Drawer
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

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
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeScreen()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
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

            R.id.nav_profile -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Profile())
                .commit()

            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeScreen())
                .commit()

            R.id.nav_log_out -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeScreen())
                .commit()

            R.id.nav_my_cart -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MyCart())
                .commit()

            R.id.nav_my_wishlist -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MyWishlist())
                .commit()

            R.id.nav_headset -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Headset())
                .commit()

            R.id.nav_mouse -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Mouse())
                .commit()

            R.id.nav_keyboard -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Keyboard())
                .commit()

            R.id.nav_mouse_pad -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MousePad())
                .commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}