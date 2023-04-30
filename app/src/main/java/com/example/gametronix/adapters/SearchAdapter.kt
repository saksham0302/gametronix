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
import com.example.gametronix.databinding.SearchRecyclerBinding

class SearchAdapter(val user: String, val context: Context, private val searchList: ArrayList<Product>):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var binding: SearchRecyclerBinding = SearchRecyclerBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.search_recycler, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val currentProduct = searchList[position]

        holder.binding.productImage.setImageResource(currentProduct.productImage)
        holder.binding.title.text = currentProduct.productTitle
        holder.binding.name.text = currentProduct.productName
        holder.binding.price.text = currentProduct.productPrice

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