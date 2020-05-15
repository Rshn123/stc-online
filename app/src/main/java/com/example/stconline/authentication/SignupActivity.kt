package com.example.stconline.authentication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.ViewModelProviders
import com.example.stconline.R
import com.example.stconline.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    lateinit var signUpTextView: TextView
    var fullName: EditText? = null
    var email: EditText? = null
    var phone: EditText? = null
    var password: EditText? = null
    var confirmPassword: EditText? = null
    lateinit var signUpButton: Button
    lateinit var viewHolder: UserViewModel
    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = FirebaseAuth.getInstance()

        viewHolder = ViewModelProvider(
            this,
            UserViewModel.Factory(applicationContext)
        ).get(UserViewModel::class.java)

        // for sign up button
        signUpButton = findViewById(R.id.sign_up_button)

        //for to click in login textview if accout already been created by user
        signUpTextView = findViewById(R.id.sign_up_text)

        //for edittext
        fullName = findViewById(R.id.full_name_edittext)
        email = findViewById(R.id.email_edittext)
        phone = findViewById(R.id.phone_edittext)
        password = findViewById(R.id.password_edittext)
        confirmPassword = findViewById(R.id.confirm_password_edittext)
        password!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && password!!.text.toString().length < 6 && password!!.text.toString() != "") {
                password!!.error = "Shouldnot be less than 6 character"
            }
        }

        email!!.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !android.util.Patterns.EMAIL_ADDRESS.matcher(email!!.text.toString()).matches()){
                email?.error = "Email didn't matched"
            }
        }

        confirmPassword!!.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && password != confirmPassword && confirmPassword!!.text.toString() != "") {
                confirmPassword?.error = "Password didn't matched"
            }
        }

        signUpTextView.setOnClickListener {
            startActivity(LoginActivity())
        }

        signUpButton.setOnClickListener {
            viewHolder.createUser(
                fullName!!.text.toString(),
                email!!.text.toString(),
                phone!!.text.toString().toLong(),
                password!!.text.toString()
            )

            firebaseAuth.createUserWithEmailAndPassword(email!!.text.toString(), password!!.text.toString())
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        startActivity(LoginActivity())
                    }
                    else{
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun startActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onBackPressed() {
        startActivity(LoginActivity())
    }
}
