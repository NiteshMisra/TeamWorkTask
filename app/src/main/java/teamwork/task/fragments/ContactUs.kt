package teamwork.task.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import teamwork.task.R
import teamwork.task.databinding.FragmentContactUsBinding

class ContactUs : Fragment() {

    private lateinit var binding : FragmentContactUsBinding
    private lateinit var activity1 : FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity1 = requireActivity()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact_us, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = ContactUs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+919876543210")
            startActivity(intent)
        }

        binding.email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("abc@xyz.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT,"Mail Subject")
            intent.putExtra(Intent.EXTRA_TEXT,"Enter your mail body here...")
            intent.type = "text/plain"
            intent.setPackage("com.google.android.gm")
            startActivity(intent)
        }

    }

}