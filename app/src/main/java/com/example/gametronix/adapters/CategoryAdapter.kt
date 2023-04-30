package com.example.gametronix.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gametronix.Product
import com.example.gametronix.R
import com.example.gametronix.databinding.CategoryRecyclerBinding

class CategoryAdapter(private val categoryList: ArrayList<Product>):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

        class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var binding: CategoryRecyclerBinding = CategoryRecyclerBinding.bind(itemView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.category_recycler, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val currentProduct = categoryList[position]

        holder.binding.productTitle.text = currentProduct.productTitle
        holder.binding.categoryImage.setImageResource(currentProduct.productImage)


    }
}