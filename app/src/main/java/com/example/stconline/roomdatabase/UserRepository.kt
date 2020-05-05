package com.example.stconline.roomdatabase

import android.widget.Toast
import androidx.lifecycle.LiveData

class UserRepository private constructor(private val userDao: UserDao) {
    private val userAccountLiveData: LiveData<NewUser>? = null
    var userAccount:NewUser? = null
    fun isValidUser(username: String, password: String): Boolean {
        try {
            userAccount = userDao.getUser(username)
            return userAccount!!.password == password
        }
        catch(e: NullPointerException){
            return false
        }
    }

    fun insertUser(fullName: String, email: String, phone: Long, password: String) {
        userDao.insertUser(
            NewUser(
                fullName = fullName,
                email = email,
                phoneNumber = phone,
                password = password
            )
        )
    }

    companion object {
        private var INSTANCE: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (INSTANCE == null) {
                INSTANCE = UserRepository(userDao)
            }
            return INSTANCE!!
        }
    }
}