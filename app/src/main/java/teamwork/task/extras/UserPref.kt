package teamwork.task.extras

import android.content.Context
import android.content.SharedPreferences

class UserPref {

    companion object {

        private val sharedPref : SharedPreferences
                get() =  UserApplication.getContext().getSharedPreferences("UserPref",Context.MODE_PRIVATE)

        fun userEmail(userEmail : String){
            sharedPref.edit().putString("USER_EMAIL",userEmail).apply()
        }

        fun userEmail() : String? = sharedPref.getString("USER_EMAIL","");

        fun isLoggedIn(isLoggedIn : Boolean) {
            sharedPref.edit().putBoolean("USER_IS_LOGGED_IN",isLoggedIn).apply()
        }

        fun isLoggedIn() : Boolean = sharedPref.getBoolean("USER_IS_LOGGED_IN",false)

    }

}