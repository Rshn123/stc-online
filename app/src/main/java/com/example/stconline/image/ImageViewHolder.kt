package com.example.stconline.image

import android.view.View
import android.widget.ImageView
import com.example.stconline.R
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.image_slider_layout.view.*

class ImageViewHolder(view: View) : SliderViewAdapter.ViewHolder(view) {
    var backgroundImage : ImageView = view.findViewById(R.id.iv_auto_image_slider)
}