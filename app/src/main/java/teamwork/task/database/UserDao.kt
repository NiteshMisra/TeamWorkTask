package teamwork.task.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import teamwork.task.models.User

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("Select * from User where userEmail = :email and userPassword = :password")
    fun signIn(email : String, password : String) : User?

    @Query("Select * from User where userEmail = :userEmail")
    fun getUserDetail(userEmail : String) : User?

    @Query("Select count(*) from User where userEmail = :email")
    fun checkUserExist(email : String) : Int

}