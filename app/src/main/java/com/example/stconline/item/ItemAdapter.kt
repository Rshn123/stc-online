package com.example.stconline.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stconline.R

class ItemAdapter(private val context: Context, val item: Array<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_image_name_price,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemName!!.text = item.get(position).itemName
        holder.itemPrice?.text = item.get(position).itemPrice.toString()
        holder.itemRating?.rating = item.get(position).itemRating
    }

    override fun getItemCount(): Int {
        return item.size
    }
}