package com.example.gametronix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.File

class AddData : AppCompatActivity() {

    private lateinit var add: Button
    private lateinit var category: TextView
    private lateinit var title: TextView
    private lateinit var productName: TextView
    private lateinit var price: TextView
    private lateinit var description: TextView

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

            try {
                val fin = File(fileName)
                val reader = fin.bufferedReader()
                val textList = ArrayList<String>()

                reader.forEachLine { line ->
                    val textBeforeHash = line.substringBefore("##")
                    textList.add(textBeforeHash)
                }
                reader.close()
                category.text = textList[1]
                title.text = textList[2]
                productName.text = textList[3]
                price.text = textList[4]
                description.text = textList[5]
            } catch (e: java.lang.Exception) {}
        }
    }
}