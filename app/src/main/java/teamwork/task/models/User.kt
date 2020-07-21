package teamwork.task.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @NonNull @PrimaryKey val userEmail : String,
    val userName : String,
    val userPassword : String
)