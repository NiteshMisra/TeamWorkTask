package teamwork.task.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import teamwork.task.R
import teamwork.task.activity.MainActivity
import teamwork.task.databinding.FragmentSignUpBinding
import teamwork.task.extras.AppUtils
import teamwork.task.extras.UserPref
import teamwork.task.models.User
import teamwork.task.viewmodel.UserViewModel

class SignUpFragment : Fragment() {
    
    private lateinit var binding : FragmentSignUpBinding
    private lateinit var activity1 : FragmentActivity
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity1 = requireActivity()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = SignUpFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(activity1).get(UserViewModel::class.java)

        binding.password.transformationMethod = PasswordTransformationMethod()
        binding.confirmPassword.transformationMethod = PasswordTransformationMethod()

        binding.signUpBtn.setOnClickListener {

            if (binding.email.text.toString().trim().isEmpty()){
                AppUtils.snackBar(binding.root,"Enter emailId")
                return@setOnClickListener
            }

            if (!AppUtils.isEmailValid(binding.email.text.toString().trim())){
                AppUtils.snackBar(binding.root,"Enter valid email Id")
                return@setOnClickListener
            }

            if (binding.name.text.toString().trim().isEmpty()){
                AppUtils.snackBar(binding.root,"Enter your name")
                return@setOnClickListener
            }

            if (binding.password.text.toString().isEmpty()){
                AppUtils.snackBar(binding.root,"Enter password")
                return@setOnClickListener
            }

            if (binding.confirmPassword.text.toString().isEmpty()){
                AppUtils.snackBar(binding.root,"Enter Confirm password")
                return@setOnClickListener
            }

            if (binding.password.text.toString() != binding.confirmPassword.text.toString()){
                AppUtils.snackBar(binding.root,"Password not matched")
                return@setOnClickListener
            }

            signUp()

        }

    }

    private fun signUp() {
        val user = User(binding.email.text.toString(),binding.name.text.toString(),binding.password.text.toString())
        userViewModel.signUpUser(activity1,user).observe(activity1, Observer {
            if (it != null){
                UserPref.userEmail(it.userEmail)
                UserPref.isLoggedIn(true)
                AppUtils.toast(activity1,"Registered Successfully")
                val intent = Intent(activity1,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }else{
                AppUtils.snackBar(binding.root,"User already registered")
            }
        })
    }

}