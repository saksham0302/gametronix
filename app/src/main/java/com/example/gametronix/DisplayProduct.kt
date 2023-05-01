package com.example.gametronix

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import java.io.IOException

class DisplayProduct : AppCompatActivity() {

    lateinit var brand: TextView
    lateinit var image: ImageView
    lateinit var productName: TextView
    lateinit var price: TextView
    lateinit var description: TextView
    lateinit var user: String

    private lateinit var addToWishist: Button
    private lateinit var addToCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_product)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        brand = findViewById(R.id.Brand)
        image = findViewById(R.id.image)
        productName = findViewById(R.id.ProductName)
        price = findViewById(R.id.Price)
        description = findViewById(R.id.Description)
        addToWishist = findViewById(R.id.addToWishlist)
        addToCart = findViewById(R.id.addToCart)

        val intent = intent
        brand.text = intent.getStringExtra("brand")
        image.setImageResource(intent.getIntExtra("image", 0))
        productName.text = intent.getStringExtra("productName")
        price.text = intent.getStringExtra("price")
        val desc = intent.getStringExtra("description")
        user = intent.getStringExtra("user").toString()
        description.text = desc?.replace("#", "\n\n")

        addToCart.setOnClickListener {

            val data = intent.getStringExtra("brand") + "##" +
                    intent.getIntExtra("image", 0) + "##" +
                    intent.getStringExtra("productName") + "##" +
                    intent.getStringExtra("price") + "##" +
                    intent.getStringExtra("descriptvityion") + "\n"

            try {
                val fout = openFileOutput(user + "Cart.txt", Context.MODE_APPEND)
                fout.write(data.toByteArray())
                fout.close()
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()

            } catch (to: IOException) { }
        }

        addToWishist.setOnClickListener {

            val data = intent.getStringExtra("brand") + "##" +
                    intent.getIntExtra("image", 0) + "##" +
                    intent.getStringExtra("productName") + "##" +
                    intent.getStringExtra("price") + "##" +
                    intent.getStringExtra("description") + "\n"

            try {
                val fout = openFileOutput(user + "Wishlist.txt", Context.MODE_APPEND)
                fout.write(data.toByteArray())
                fout.close()
                Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show()

            } catch (to: IOException) { }
        }
    }
}