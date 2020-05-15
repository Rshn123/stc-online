package com.example.stconline.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.stconline.image.ImageAdapter
import com.example.stconline.R
import com.example.stconline.image.Image
import com.example.stconline.item.Item
import com.example.stconline.item.ItemAdapter
import com.google.android.material.slider.Slider
import com.google.firebase.auth.FirebaseAuth
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.flash_sale_show_more.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class HomeFragment() : Fragment() {

    lateinit var imageAdapter: ImageAdapter
    private lateinit var itemView: RecyclerView
    lateinit var timer: Timer

    val item = Array(16) { Item() }
    private val images = Array(5){Image()}
    lateinit var sliderView: SliderView
    var millisInFuture: Long = 847000

    //View to pollute with minutes, second and hour
    lateinit var minute: TextView
    lateinit var second: TextView
    lateinit var hour: TextView


    //Firebase for logging out

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Item recycle view
        itemView = view!!.findViewById(R.id.item_recycle_view)
        //Slider view
        sliderView = view!!.findViewById(R.id.image_slider)

        second = view!!.findViewById(R.id.second)
        minute = view!!.findViewById(R.id.minute)
        hour = view!!.findViewById(R.id.hour)


        //
        imageAdapter = ImageAdapter(context!!)

        timer(millisInFuture, 1000).start()

        //Method to perfrom various things

        addItem()
        setRecycleView()
        setSliderView()

    }

    private fun timer(millisInFutures: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFutures, countDownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                millisInFuture = millisUntilFinished
                hour.text = (String.format(Locale.getDefault(), "%02d",(millisInFuture / 1000) / 360))
                minute.text = (String.format(Locale.getDefault(), "%02d", (millisInFuture / 1000) / 60))
                second.text = (String.format(Locale.getDefault(), "%02d", (millisInFuture / 1000) % 60))
            }

            override fun onFinish() {
                hour.text= ""
            }

        }
    }

    private fun setSliderView() {
        sliderView.setSliderAdapter(imageAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.YELLOW
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 4
        sliderView.startAutoCycle()
    }

    private fun setRecycleView() {

        item_recycle_view?.layoutManager = LinearLayoutManager(context)
        item_recycle_view?.layoutManager = GridLayoutManager(context, 2)
        item_recycle_view?.adapter = ItemAdapter(context!!, item)
        item_recycle_view.isNestedScrollingEnabled = true
        item_recycle_view.smoothScrollToPosition(0)

    }

    private fun addItem() {
        item[0] = Item("Banana", 1400.0, 1.5F)
        item[1] = Item("Panana", 1400.0, 1.5F)
        item[2] = Item("Lanana", 2400.0, 2.5F)
        item[3] = Item("Canana", 2400.0, 3.5F)
        item[4] = Item("Danana", 3400.0, 4.5F)
        item[5] = Item("Eanana", 4400.0, 2.5F)
        item[6] = Item("Ganana", 5400.0, 3.5F)
        item[7] = Item("Fanana", 6400.0, 3.5F)
        item[8] = Item("Hanana", 7400.0, 4.8F)
        item[9] = Item("Ianana", 8400.0, 3.5F)
        item[10] = Item("Janana", 9400.0, 2.0F)
        item[11] = Item("Kanana", 10400.0, 1.5F)
        item[12] = Item("Lanana", 11400.0, 1.5F)
        item[13] = Item("Manana", 12400.0, 2.5F)
        item[14] = Item("Nanana", 13400.0, 3.5F)
        item[15] = Item("Oanana", 14400.0, 4.5F)
    }

}
