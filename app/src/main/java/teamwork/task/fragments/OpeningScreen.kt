package teamwork.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import teamwork.task.R
import teamwork.task.activity.LoginActivity
import teamwork.task.databinding.FragmentOpeningScreenBinding

class OpeningScreen : Fragment() {

    private lateinit var binding : FragmentOpeningScreenBinding
    private lateinit var activity1 : FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity1 = requireActivity()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_opening_screen, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = OpeningScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.signInBtn.setOnClickListener { 
            if (activity1 is LoginActivity){
                (activity1 as LoginActivity).addFragment(1)
            }
        }
        
        binding.signUpBtn.setOnClickListener {
            if (activity1 is LoginActivity){
                (activity1 as LoginActivity).addFragment(2)
            }
        }
    }
    
}