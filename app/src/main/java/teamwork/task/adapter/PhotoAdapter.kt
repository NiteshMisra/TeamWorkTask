package teamwork.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import teamwork.task.R
import teamwork.task.models.PhotosModel

class PhotoAdapter(private var photosList: List<PhotosModel>, var context: Context) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.photoImage)
        val title: TextView = view.findViewById(R.id.photoTitle)
        val id: TextView = view.findViewById(R.id.photoId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.element_photos, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val currentItem = photosList[position]
        Glide.with(context)
            .load(currentItem.thumbnailUrl)
            .into(holder.image)
        holder.title.text = (("TITLE : ${currentItem.title}"))
        holder.id.text = (("ID : ${currentItem.id}"))

    }

}