package com.example.stconline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.PagerAdapter

class ImageAdapter() : PagerAdapter() {
    lateinit var context: Context
    lateinit var inflator: LayoutInflater
    var custom_position = 0
    constructor(context: Context) : this() {
        this.context = context
    }

    val image_resource = arrayListOf(
        R.drawable.sample_06,
        R.drawable.sample_02,
        R.drawable.sample_03,
        R.drawable.sample_04,
        R.drawable.sample_05
    )


    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if(custom_position>4)
            custom_position = 0

        
        inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)!! as LayoutInflater
        val item_view: View = inflator.inflate(R.layout.image_view_layout, container, false)
        val imageView = item_view.findViewById(R.id.image_view) as ImageView
        imageView.setImageResource(image_resource[custom_position])
        custom_position++
        container.addView(item_view)
        return item_view

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        return container.removeView(`object` as LinearLayout)
    }
}

