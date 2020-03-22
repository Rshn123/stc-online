package com.example.stconline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_view_layout.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    var imageAdapter = ImageAdapter(this)
    lateinit var timer: Timer
    lateinit var linearLayout: LinearLayout
    var current_position = 0
    var custom_position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.image_viewpager)
        viewPager.adapter = imageAdapter
        prepareDots(custom_position++)
        slideShow()
        viewPager.addOnPageChangeListener(object:OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(custom_position>4){
                    custom_position = 0
                }
                prepareDots(custom_position++)
            }

        })
    }

    private fun slideShow() {
        val handler = Handler()
        val runnable = Runnable {
            if (current_position == Integer.MAX_VALUE) {
                current_position = 0
            }
            viewPager.setCurrentItem(current_position++, true)
        }
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 250, 2500)

    }

    private fun prepareDots(currentSlidePosition: Int) {
        if (dot_container!!.childCount > 0) {
            dot_container!!.removeAllViews()
        }

        var dots = Array(5){ ImageView(this) }
        for (i in 0..4) {
            dots[i] = ImageView(this)
            if (i == currentSlidePosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot))
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.not_active_dot))
            }

            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(4, 0, 4, 0)
            dot_container.addView(dots[i], layoutParams)

        }
    }

}

