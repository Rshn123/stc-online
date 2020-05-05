package com.example.stconline

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stconline.roomdatabase.UserAccountDatabase
import com.example.stconline.roomdatabase.UserRepository

class UserViewModel(context: Context) : ViewModel() {

    private val userRepository: UserRepository

    init {
        userRepository =
            UserRepository.getInstance(UserAccountDatabase.getAppDatabase(context).userDao())
    }

    internal fun createUser(fullName: String, email: String, phone: Long, password: String) {
        userRepository.insertUser(fullName, email, phone, password)
    }

    internal fun checkValidLogin(username:String, password:String):Boolean{
        return userRepository.isValidUser(username, password)
    }

    class Factory internal constructor(ctxt:Context):ViewModelProvider.Factory{
        private val ctxt :Context
        init{
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         return UserViewModel(ctxt) as T
        }
    }

}