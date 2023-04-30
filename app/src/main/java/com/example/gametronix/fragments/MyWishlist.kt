package com.example.gametronix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gametronix.Product
import com.example.gametronix.R
import com.example.gametronix.adapters.SearchAdapter
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MyWishlist(val user: String) : Fragment() {

    lateinit var v: View

    private val fileName = user + "Wishlist.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_my_wishlist, container, false)

        val con = v.context

        try {
            val fin = requireContext().openFileInput(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(fin))
            val product = ArrayList<Product>()

            bufferedReader.forEachLine { line ->
                val arr = line.split("##")
                val listItem = Product("", arr[0], arr[2], arr[3], arr[4], arr[1].toInt())
                product.add(listItem)
            }

            bufferedReader.close()
            fin.close()

            val prod = v.findViewById<View>(R.id.wishlistRecycler) as RecyclerView
            prod.layoutManager = LinearLayoutManager(con, LinearLayoutManager.VERTICAL, false)
            prod.adapter = SearchAdapter(user, con, product)

        } catch (to: IOException) {
            Toast.makeText(con, "Your wishlist is empty!", Toast.LENGTH_SHORT).show()
        }

        // Inflate the layout for this fragment
        return v
    }

}