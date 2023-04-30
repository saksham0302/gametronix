package com.example.gametronix.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gametronix.Product
import com.example.gametronix.R
import com.example.gametronix.adapters.CategoryAdapter
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeScreen : Fragment() {

    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home_screen, container, false)

        val con = v.context

        bannerChange(v)

        displayProductCategory(con,"headset", R.id.headsetRecycler, R.drawable.headset)
        displayProductCategory(con,"keyboard", R.id.keyboardRecycler, R.drawable.keyboard)
        displayProductCategory(con,"mouse", R.id.mouseRecycler, R.drawable.mouse)
        displayProductCategory(con,"mousepad", R.id.mousePadRecycler, R.drawable.mouse_pad)



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



    private val fileName = "ProductData.txt"

    private fun displayProductCategory(con: Context, category: String, recyclerId: Int, srcId: Int) {

        val fin = requireContext().openFileInput(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(fin))
        val product = ArrayList<Product>()

        bufferedReader.forEachLine { line ->
            val textBeforeHash = line.substringBefore("##")
            if ( textBeforeHash == category) {
                val arr = line.split("##")
                val listItem = Product(arr[0], arr[1], arr[2], arr[3], arr[4], srcId)
                product.add(listItem)
            }
        }
        bufferedReader.close()
        fin.close()

        val prod = v.findViewById<View>(recyclerId) as RecyclerView
        prod.layoutManager = LinearLayoutManager(con, LinearLayoutManager.HORIZONTAL, false)
        prod.adapter = CategoryAdapter(con, product)

    }
}