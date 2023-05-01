package com.example.gametronix.fragments

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gametronix.NavigationDrawer
import com.example.gametronix.Product
import com.example.gametronix.R
import com.example.gametronix.adapters.SearchAdapter
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class MyCart(val user: String) : Fragment() {

    lateinit var v: View
    lateinit var makePayment: Button
    private val fileName = user + "Cart.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_my_cart, container, false)

        val con = v.context
        var toastDisplay = 0
        var sum = 0.0

        try {
            val fin = requireContext().openFileInput(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(fin))
            val product = ArrayList<Product>()

            bufferedReader.forEachLine { line ->
                val arr = line.split("##")
                val listItem = Product("", arr[0], arr[2], arr[3], arr[4], arr[1].toInt())
                product.add(listItem)

                val priceArr = arr[3].split(" ")
                val price = priceArr[1].replace(",", "").toFloat()
                sum += price

                toastDisplay += 1
            }

            bufferedReader.close()
            fin.close()

            val prod = v.findViewById<View>(R.id.cartRecycler) as RecyclerView
            prod.layoutManager = LinearLayoutManager(con, LinearLayoutManager.VERTICAL, false)
            prod.adapter = SearchAdapter(user, con, product)
            
        } catch (to: IOException) {
            Toast.makeText(con, "Your cart is empty!", Toast.LENGTH_SHORT).show()
        }

        makePayment = v.findViewById(R.id.makePayment)
        val displaySum = String.format("%.2f", sum)

        makePayment.setOnClickListener {

            if (toastDisplay < 1) {
                Toast.makeText(con, "Your cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                val alertDialog = AlertDialog.Builder(con).create()
                alertDialog.setTitle("Make Payment")
                alertDialog.setMessage("Your payment is of ₹ ${displaySum}.\nPress yes to proceed!")

                alertDialog.setButton(
                    AlertDialog.BUTTON_POSITIVE, "Yes"
                ) { dialog, which ->
                    makingPurchase(con)
                }

                alertDialog.setButton(
                    AlertDialog.BUTTON_NEGATIVE, "No"
                ) { dialog, which -> dialog.dismiss() }
                alertDialog.show()
            }
        }

        // Inflate the layout for this fragment
        return v
    }

    private fun makingPurchase(con: Context) {
        Toast.makeText(con, "Purchase made!", Toast.LENGTH_SHORT).show()

        val file = File(con.filesDir, user + "Cart.txt")
        try {
            file.delete()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val intent = Intent(con, NavigationDrawer::class.java)
        intent.putExtra("userName", user)
        startActivity(intent)
    }
}