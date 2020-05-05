package com.example.stconline.roomdatabase
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg newUser: NewUser)

    @Update
    fun updateUser(vararg newUser: NewUser)

    @Delete
    fun deleteUser(vararg newUser: NewUser)

    @Query("SELECT * FROM NewUser WHERE email= :emailAddress")
    fun getUser(emailAddress: String):NewUser

}
