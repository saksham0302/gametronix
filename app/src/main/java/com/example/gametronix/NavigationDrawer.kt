package com.example.gametronix

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var showSearch: ConstraintLayout

    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

        intent = getIntent()

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

        val user = intent.getStringExtra("userName").toString()

        when (item.itemId) {

            R.id.nav_profile -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Profile(user))
                .commit()
            }

            R.id.nav_home -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeScreen())
                    .commit()
            }

            R.id.nav_search -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchDisplay(""))
                    .commit()
            }

            R.id.nav_log_out -> logOut()

            R.id.nav_my_cart -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MyCart(user))
                    .commit()
            }

            R.id.nav_my_wishlist -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MyWishlist(user))
                    .commit()
            }

            R.id.nav_headset -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchDisplay("headset"))
                    .commit()
            }

            R.id.nav_mouse -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchDisplay("mouse"))
                    .commit()
            }

            R.id.nav_keyboard -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchDisplay("keyboard"))
                    .commit()
            }

            R.id.nav_mouse_pad -> {
                showSearch = findViewById(R.id.showSearch)
                showSearch.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchDisplay("mousepad"))
                    .commit()
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logOut() {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Log Out")
        alertDialog.setMessage("Are you sure, you want to log out?")

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes"
        ) { dialog, which ->
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
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