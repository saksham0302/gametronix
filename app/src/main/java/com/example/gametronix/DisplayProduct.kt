package com.example.gametronix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DisplayProduct : AppCompatActivity() {

    lateinit var brand: TextView
    lateinit var image: ImageView
    lateinit var productName: TextView
    lateinit var price: TextView
    lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_product)

        brand = findViewById(R.id.Brand)
        image = findViewById(R.id.image)
        productName = findViewById(R.id.ProductName)
        price = findViewById(R.id.Price)
        description = findViewById(R.id.Description)

        val intent = intent
        brand.text = intent.getStringExtra("brand")
        image.setImageResource(intent.getIntExtra("image", 0))
        productName.text = intent.getStringExtra("productName")
        price.text = intent.getStringExtra("price")
        val desc = intent.getStringExtra("description")
        description.text = desc?.replace("#", "\n")
    }
}