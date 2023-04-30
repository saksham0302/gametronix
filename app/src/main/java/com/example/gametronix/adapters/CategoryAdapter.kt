package com.example.gametronix.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gametronix.DisplayProduct
import com.example.gametronix.Product
import com.example.gametronix.R
import com.example.gametronix.databinding.CategoryRecyclerBinding

class CategoryAdapter(val user: String, val context: Context, private val categoryList: ArrayList<Product>):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

        class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var binding: CategoryRecyclerBinding = CategoryRecyclerBinding.bind(itemView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.category_recycler, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val currentProduct = categoryList[position]

        holder.binding.productTitle.text = currentProduct.productTitle
        holder.binding.categoryImage.setImageResource(currentProduct.productImage)

        holder.binding.cardView.setOnClickListener {

            val intent = Intent(context, DisplayProduct::class.java)
            intent.putExtra("brand", currentProduct.productTitle)
            intent.putExtra("image", currentProduct.productImage)
            intent.putExtra("productName", currentProduct.productName)
            intent.putExtra("price", currentProduct.productPrice)
            intent.putExtra("description", currentProduct.productDescription)
            intent.putExtra("user", user)
            context.startActivity(intent)
        }
    }
}