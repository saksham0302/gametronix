package com.example.gametronix

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.IOException

class AddData : AppCompatActivity() {

    private lateinit var add: Button
    private lateinit var category: EditText
    private lateinit var title: EditText
    private lateinit var productName: EditText
    private lateinit var price: EditText
    private lateinit var description: EditText

    private lateinit var productData: String
    private val fileName = "ProductData.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        add = findViewById(R.id.add)
        category = findViewById(R.id.category)
        title = findViewById(R.id.title)
        productName = findViewById(R.id.name)
        price = findViewById(R.id.price)
        description = findViewById(R.id.description)

        add.setOnClickListener {

            if (category.text.isEmpty() && title.text.isEmpty() && productName.text.isEmpty() &&
                price.text.isEmpty() && description.text.isEmpty()
            ) {

                Toast.makeText(this, "Fill the details properly", Toast.LENGTH_LONG).show()

            } else {
                productData = ""
                productData += category.text.toString()
                productData += "##"
                productData += title.text.toString()
                productData += "##"
                productData += productName.text.toString()
                productData += "##"
                productData += price.text.toString()
                productData += "##"
                productData += description.text.toString()
                productData += "##"


                try {
                    val fout = openFileOutput(fileName, Context.MODE_APPEND)
                    fout.write(productData.toByteArray())
                    fout.close()

                    Toast.makeText(this, "Visitor Added", Toast.LENGTH_LONG).show()
                } catch (to: IOException) { }

                category.text.clear()
                title.text.clear()
                productName.text.clear()
                price.text.clear()
                description.text.clear()
            }
        }
    }
}