@file:Suppress("DEPRECATION")

package teamwork.task.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import teamwork.task.R
import teamwork.task.databinding.FragmentImagesBinding

class Images : Fragment() {

    private lateinit var binding : FragmentImagesBinding
    private lateinit var activity1 : FragmentActivity
    private val selectImageCode = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity1 = requireActivity()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_images, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = Images()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.galleryBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Select Picture"),selectImageCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == selectImageCode){
            if (resultCode == Activity.RESULT_OK && data != null){
                try {

                    val selectPhotoUri = data.data
                    if (Build.VERSION.SDK_INT < 28){
                        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver,selectPhotoUri)
                        binding.image.setImageBitmap(bitmap)
                        binding.image.visibility = View.VISIBLE
                        binding.galleryBtn.visibility = View.GONE
                    }else{
                        val source = ImageDecoder.createSource(context?.contentResolver!!,selectPhotoUri!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding.image.setImageBitmap(bitmap)
                        binding.image.visibility = View.VISIBLE
                        binding.galleryBtn.visibility = View.GONE
                    }

                }catch (e : NullPointerException){
                    Toast.makeText(context,"Some error occurred, Try again later",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}