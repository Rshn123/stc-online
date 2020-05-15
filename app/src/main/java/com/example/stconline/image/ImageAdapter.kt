package com.example.stconline.image

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.stconline.R
import com.smarteist.autoimageslider.SliderViewAdapter
import java.net.URL

class ImageAdapter(private val context: Context) :
    SliderViewAdapter<ImageViewHolder>() {
    val array = arrayOf(
        Image(R.drawable.sample_01),
        Image(R.drawable.sample_02),
        Image(R.drawable.sample_03),
        Image(R.drawable.sample_04),
        Image(R.drawable.sample_05)
    )

    override fun onCreateViewHolder(parent: ViewGroup?): ImageViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.image_slider_layout, null)
        return ImageViewHolder(inflater)
    }

    override fun onBindViewHolder(viewHolder: ImageViewHolder?, position: Int) {
        viewHolder!!.backgroundImage.setImageResource(array[position].imageName)
    }

    override fun getCount(): Int {
        return array.size
    }


}

