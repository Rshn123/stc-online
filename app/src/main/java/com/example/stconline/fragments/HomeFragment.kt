package com.example.stconline.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment() : Fragment() {


    lateinit var imageAdapter: ImageAdapter
    private lateinit var itemView: RecyclerView
    lateinit var timer: Timer

    val item = Array(16, { Item() })
    val images = Array(5, { Image() })
    lateinit var sliderView: SliderView

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
        //
        imageAdapter = ImageAdapter(context!!, images)


        //Method to perfrom various things

        addItem()
        setRecycleView()
        setSliderView()
        addImage()

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

    }

    fun addItem() {
        item.set(0, Item("Banana", 1400.0, 1.5F))
        item.set(1, Item("Banana", 1400.0, 1.5F))
        item.set(2, Item("Canana", 2400.0, 2.5F))
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
        images.set(0, Image(R.drawable.sample_02))
        images.set(1, Image(R.drawable.sample_03))
        images.set(2, Image(R.drawable.sample_04))
        images.set(3, Image(R.drawable.sample_05))
        images.set(4, Image(R.drawable.sample_06))
    }

}
