package com.example.gametronix.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.gametronix.R

class HomeScreen : Fragment() {

    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home_screen, container, false)
        bannerChange(v)
        // Inflate the layout for this fragment
        return v
    }

    // Banner
    lateinit var banner: ImageView

    private fun bannerChange(view: View) {
        banner = view.findViewById(R.id.banner)
        val bannerArray = intArrayOf(
            R.drawable.banner1, R.drawable.banner2, R.drawable.banner3,
            R.drawable.banner4, R.drawable.banner5
        )
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