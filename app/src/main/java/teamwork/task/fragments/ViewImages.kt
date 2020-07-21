package teamwork.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import teamwork.task.R
import teamwork.task.adapter.PhotoAdapter
import teamwork.task.databinding.FragmentViewImagesBinding
import teamwork.task.viewmodel.UserViewModel

class ViewImages : Fragment() {

    private lateinit var binding : FragmentViewImagesBinding
    private lateinit var activity1 : FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity1 = requireActivity()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_images, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = ViewImages()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userViewModel : UserViewModel = ViewModelProvider(activity1).get(UserViewModel::class.java)

        binding.photoRcv.layoutManager = LinearLayoutManager(activity1)
        userViewModel.getPhotos().observe(activity1, Observer {
            it?.let {
                val adapter = PhotoAdapter(it,activity1)
                binding.photoRcv.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }
}