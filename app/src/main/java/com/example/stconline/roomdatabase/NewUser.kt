package com.example.stconline.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewUser(
    val fullName:String,
    @PrimaryKey
    val email:String,
    val phoneNumber:Long,
    val password:String
)