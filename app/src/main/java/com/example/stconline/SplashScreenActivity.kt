package com.example.stconline

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stconline.authentication.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("loginAdmin",0)

        val timer: Thread = object :Thread(){
            override fun run() {
                try{
                    sleep(5000)
                }
                catch (e: InterruptedException){
                    e.printStackTrace()
                }
                finally {
                    afterUserLoggedIn()
                }
            }
        }
        timer.start()
    }

    fun afterUserLoggedIn(){
        if(sharedPreferences.getBoolean("userLoggedIn", false) && firebaseAuth.currentUser != null){
           startActivity(Intent(this,MainActivity::class.java))
        }
        else{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
