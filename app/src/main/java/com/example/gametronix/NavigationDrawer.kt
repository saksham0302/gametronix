package com.example.gametronix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
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
    private lateinit var searchText: EditText
    private lateinit var searchButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

        search()

        navigation(savedInstanceState)
    }

    private fun navigation(savedInstanceState: Bundle?) {
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
            navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            if (navigationView.menu.findItem(R.id.nav_home).isChecked)
                super.onBackPressed()
            else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeScreen())
                    .commit()
                navigationView.setCheckedItem(R.id.nav_home)
            }
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

            R.id.nav_search -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchDisplay(""))
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

            R.id.nav_headset ->
                supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchDisplay("headset"))
                .commit()

            R.id.nav_mouse -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchDisplay("mouse"))
                .commit()

            R.id.nav_keyboard -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchDisplay("keyboard"))
                .commit()

            R.id.nav_mouse_pad -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchDisplay("mousepad"))
                .commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun search() {

        searchText = findViewById(R.id.search)
        searchButton = findViewById(R.id.searchButton)

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        searchButton.setOnClickListener {

            val text = searchText.text.toString()

            if (text.isEmpty())
                Toast.makeText(this, "Search string is empty", Toast.LENGTH_SHORT).show()
            else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchDisplay(text))
                    .commit()
                searchText.text.clear()
                navigationView.setCheckedItem(R.id.nav_search)
            }
        }
    }
}