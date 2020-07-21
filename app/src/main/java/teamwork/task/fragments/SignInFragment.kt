package teamwork.task.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import teamwork.task.R
import teamwork.task.activity.MainActivity
import teamwork.task.databinding.FragmentSignInBinding
import teamwork.task.extras.AppUtils
import teamwork.task.extras.UserPref
import teamwork.task.viewmodel.UserViewModel

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var activity1: FragmentActivity
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity1 = requireActivity()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(activity1).get(UserViewModel::class.java)

        binding.password.transformationMethod = PasswordTransformationMethod()

        binding.signInBtn.setOnClickListener {

            if (binding.email.text.toString().isEmpty()) {
                AppUtils.toast(activity1, "Enter emailId")
                return@setOnClickListener
            }

            if (!AppUtils.isEmailValid(binding.email.text.toString())) {
                AppUtils.toast(activity1, "Enter valid emailId")
                return@setOnClickListener
            }

            if (binding.password.text.toString().isEmpty()) {
                AppUtils.toast(activity1, "Enter password")
                return@setOnClickListener
            }

            login()

        }

    }

    private fun login() {

        userViewModel.signInUser(
            activity1,
            binding.email.text.toString(),
            binding.password.text.toString()
        ).observe(activity1, Observer {
            if (it != null) {
                UserPref.userEmail(it.userEmail)
                UserPref.isLoggedIn(true)
                AppUtils.toast(activity1,"Logged In Successfully")
                val intent = Intent(activity1, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                AppUtils.snackBar(binding.root, "Credential not matched")
            }
        })
    }

}