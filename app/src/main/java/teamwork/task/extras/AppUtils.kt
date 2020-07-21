package teamwork.task.extras

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class AppUtils {

    companion object {

        fun isEmailValid(emailId : String) : Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(emailId)
            return matcher.matches()
        }


        fun toast(context: Context, message : String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }

        fun snackBar(root : View, title : String){
            val snackBar = Snackbar.make(root,title,Snackbar.LENGTH_SHORT)
            snackBar.show()
        }

    }

}