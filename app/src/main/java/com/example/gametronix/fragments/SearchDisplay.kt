package com.example.gametronix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gametronix.Product
import com.example.gametronix.R
import com.example.gametronix.adapters.SearchAdapter
import java.io.BufferedReader
import java.io.InputStreamReader

class SearchDisplay(var search: String) : Fragment() {

    lateinit var v: View
    lateinit var searchResult: TextView
    private val fileName = "ProductData.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_search_display, container, false)

        val con = v.context

        searchResult = v.findViewById(R.id.searchResult)
        searchResult.text = ""
        searchResult.text = "Showing results for \"${search}\""

        val fin = requireContext().openFileInput(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(fin))
        val product = ArrayList<Product>()

        bufferedReader.forEachLine { line ->
            val arr = line.split("##")

            if (arr[0].equals(search, true) || arr[1].equals(search, true) ||
                arr[2].equals(search, true)) {

                var srcId: Int = 0

                when (arr[0]) {

                    "keyboard" -> srcId = R.drawable.keyboard
                    "mouse" -> srcId = R.drawable.mouse
                    "mousepad" -> srcId = R.drawable.mouse_pad
                    "headset" -> srcId = R.drawable.headset
                }
                val listItem = Product(arr[0], arr[1], arr[2], arr[3], arr[4], srcId)
                product.add(listItem)
            }
        }
        bufferedReader.close()
        fin.close()

        val prod = v.findViewById<View>(R.id.searchRecycler) as RecyclerView
        prod.layoutManager = LinearLayoutManager(con, LinearLayoutManager.VERTICAL, false)
        prod.adapter = SearchAdapter(con, product)

        // Inflate the layout for this fragment
        return v
    }
}