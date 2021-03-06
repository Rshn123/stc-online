package com.example.stconline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.stconline.fragments.AccountFragment
import com.example.stconline.fragments.CartFragment
import com.example.stconline.fragments.HomeFragment
import com.example.stconline.fragments.MessageFragment
import com.example.stconline.item.Item
import com.example.stconline.item.ItemAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.flash_sale_show_more.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var navigation: BottomNavigationView
    lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName).commit()
        }

        navigation = findViewById(R.id.bottom_navigation_view)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val fragment = HomeFragment()
                    fragmentInitalizer(fragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_account -> {
                    val fragment = AccountFragment()
                    fragmentInitalizer(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_message -> {
                    val fragment = MessageFragment()
                    fragmentInitalizer(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_cart -> {
                    val fragment = CartFragment()
                    fragmentInitalizer(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                else ->
                    false
            }

        }

    private fun fragmentInitalizer(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName).commit()
    }

}

