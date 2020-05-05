package com.example.stconline.authentication

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stconline.MainActivity
import com.example.stconline.R
import com.example.stconline.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var signUpTextView: TextView
    private lateinit var loginButton: Button
    lateinit var username: EditText
    lateinit var password: EditText
    private var viewHolder:UserViewModel? = null
    var sharedPreferences:SharedPreferences? = null
    val PREFES_NAME = "loginState"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //SharedPreference to store the login state
        sharedPreferences = getSharedPreferences(PREFES_NAME, 0)
        val editor:SharedPreferences.Editor = sharedPreferences!!.edit()
        viewHolder = ViewModelProvider(this, UserViewModel.Factory(applicationContext)).get(UserViewModel::class.java)
        //for login button
        loginButton = findViewById(R.id.login_button)

        //for user login username and password edittext
        password = findViewById(R.id.password_edittext)
        username = findViewById(R.id.username_edittext)

        //for signup form if user donot have an account
        signUpTextView = findViewById(R.id.sign_up_text)

        //when user click the "sign up? if you donot have an account text view"
        signUpTextView.setOnClickListener {
            startActivity(SignupActivity())
        }

        username.setOnFocusChangeListener{_, hasFocus ->
            if(!hasFocus && !android.util.Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
                username.error = "Not a valid Email Address"
            }
        }

        //when login button is clicked
        loginButton.setOnClickListener{
            val isValidOrNot = viewHolder!!.checkValidLogin(username.text.toString(), password.text.toString())
            if(isValidOrNot){
                startActivity(MainActivity())
                editor.putBoolean("loginActive", true).apply()
            }
            else{
                username.error = "The email or phone number you've entered doesn't match any account. Sign up for an account."
            }
        }
    }

    // method to start the activity when sign up textview is clicked
    fun startActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
        finish()
    }

    //when user presses the back from the mobile phone inside this loginActivity
    override fun onBackPressed() {

        //for creating the pop up dialog when back is pressed
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to close application ?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
