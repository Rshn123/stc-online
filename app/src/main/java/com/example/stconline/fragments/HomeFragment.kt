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
    val images = ArrayList<Image>()
    lateinit var sliderView: SliderView
    var millisInFuture: Long = 847000

    //View to pollute with minutes, second and hour
    lateinit var minute: TextView
    lateinit var second: TextView
    lateinit var hour: TextView

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
        imageAdapter = ImageAdapter(context!!, images)

        timer(millisInFuture, 1000).start()

        //Method to perfrom various things

        addItem()
        setRecycleView()
        setSliderView()
        addImage()

    }

    private fun timer(millisInFutures: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFutures, countDownInterval) {


            override fun onTick(millisUntilFinished: Long) {
                millisInFuture = millisUntilFinished
                hour.text = (String.format(Locale.getDefault(), "%02d",(millisInFuture / 1000) / 360))
                Log.d("154" ,"${hour.text}")
                minute.text = (String.format(Locale.getDefault(), "%02d", (millisInFuture / 1000) / 60))
                Log.d("154" ,"${minute.text}")
                second.text = (String.format(Locale.getDefault(), "%02d", (millisInFuture / 1000) % 60))
                Log.d("154" ,"${second.text}")

            }

            override fun onFinish() {
                hour.text= ""
            }

        }
    }

    fun setSliderView() {
        sliderView.setSliderAdapter(imageAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.YELLOW
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 4
        sliderView.startAutoCycle()
    }

    fun setRecycleView() {

        item_recycle_view?.layoutManager = LinearLayoutManager(context)
        item_recycle_view?.layoutManager = GridLayoutManager(context, 2)
        item_recycle_view?.adapter = ItemAdapter(context!!, item)
        item_recycle_view.smoothScrollToPosition(0)

    }

    fun addItem() {
        item.set(0, Item("Banana", 1400.0, 1.5F))
        item.set(1, Item("Panana", 1400.0, 1.5F))
        item.set(2, Item("Lanana", 2400.0, 2.5F))
        item.set(3, Item("Canana", 2400.0, 3.5F))
        item.set(4, Item("Danana", 3400.0, 4.5F))
        item.set(5, Item("Eanana", 4400.0, 2.5F))
        item.set(6, Item("Ganana", 5400.0, 3.5F))
        item.set(7, Item("Fanana", 6400.0, 3.5F))
        item.set(8, Item("Hanana", 7400.0, 4.8F))
        item.set(9, Item("Ianana", 8400.0, 3.5F))
        item.set(10, Item("Janana", 9400.0, 2.0F))
        item.set(11, Item("Kanana", 10400.0, 1.5F))
        item.set(12, Item("Lanana", 11400.0, 1.5F))
        item.set(13, Item("Manana", 12400.0, 2.5F))
        item.set(14, Item("Nanana", 13400.0, 3.5F))
        item.set(15, Item("Oanana", 14400.0, 4.5F))

    }

    fun addImage() {
        fun addImage() {
            images.set(0, Image(R.drawable.sample_02))
            images.set(1, Image(R.drawable.sample_03))
            images.set(2, Image(R.drawable.sample_04))
            images.set(3, Image(R.drawable.sample_05))
            images.set(4, Image(R.drawable.sample_06))
        }


    }

}
