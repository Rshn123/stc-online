package com.example.stconline.fragments

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.stconline.R
import com.example.stconline.authentication.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class AccountFragment : Fragment() {

    lateinit var signOut: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        signOut = view!!.findViewById(R.id.sign_out)
        sharedPreferences = activity!!.getSharedPreferences("loginAdmin",0)

        //Instantiating the firebase
        firebaseAuth = FirebaseAuth.getInstance()

        signOut.setOnClickListener{
            signOut()
        }
    }

    private fun signOut(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage("Do you sign out?")
        alertDialog.setCancelable(true)
        alertDialog.setPositiveButton("Yes"){_,_->
            sharedPreferences.edit().putBoolean("userLoggedIn", false).apply()
            startActivity(Intent(context, LoginActivity::class.java))
            firebaseAuth.signOut()
            activity!!.finishAffinity()
        }
        alertDialog.setNegativeButton("No"){dialog, _ ->
            dialog.cancel()
        }

        val dialog: AlertDialog = alertDialog.create()
        dialog.show()
    }

}
