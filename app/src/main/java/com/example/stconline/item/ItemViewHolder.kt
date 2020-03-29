package com.example.stconline.item

import android.media.Rating
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stconline.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var itemName: TextView? = view.findViewById(R.id.item_name)
    var itemPrice: TextView? = view.findViewById(R.id.item_price2)
    var itemRating: RatingBar? = view.findViewById(R.id.rating_bar)

}
