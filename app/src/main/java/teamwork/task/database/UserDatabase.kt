package teamwork.task.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import teamwork.task.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun playListDao(): UserDao

    companion object {

        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "UserDB"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

    }


}