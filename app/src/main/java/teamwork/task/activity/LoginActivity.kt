package teamwork.task.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import teamwork.task.R
import teamwork.task.fragments.OpeningScreen
import teamwork.task.fragments.SignInFragment
import teamwork.task.fragments.SignUpFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        fragmentManager = supportFragmentManager

        addFragment(0)
    }

    fun addFragment(position : Int){
        when(position) {
            0 -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.login_container,OpeningScreen.newInstance())
                    .commit()
            }
            1 -> {
                fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .replace(R.id.login_container,SignInFragment.newInstance())
                    .addToBackStack("signIn")
                    .commit()
            }
            2 -> {
                fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .replace(R.id.login_container,SignUpFragment.newInstance())
                    .addToBackStack("signUp")
                    .commit()
            }
        }
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1){
            fragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }
}