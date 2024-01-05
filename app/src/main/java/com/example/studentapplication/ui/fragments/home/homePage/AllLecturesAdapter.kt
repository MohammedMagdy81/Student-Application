package com.example.studentapplication.ui.fragments.home.homePage

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.databinding.ItemLayoutLecturesBinding
import com.squareup.picasso.Picasso

class AllLecturesAdapter(
    var itemsList: List<GetLecturesResponseItem>?,
    var onItemClick: ((GetLecturesResponseItem) -> Unit)? = null
) : RecyclerView.Adapter<AllLecturesAdapter.AllLecturesViewHolder>() {

    inner class AllLecturesViewHolder(val itemBinding: ItemLayoutLecturesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(currentLecture: GetLecturesResponseItem?) {
            currentLecture?.let {
                itemBinding.apply {
                    lecturesTeacherName.text = it.teacherName
                    lecturesPrice.text = "${it.lecture_Price} LE"
                    lecturesName.text = it.lecture_Name
                    subjectName.text = it.lecture_Subject
                    Glide.with(itemBinding.root.context).load(currentLecture.pictureUrl)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Log.d("GlideException", "$e")
                                Log.d("GlideException", "${e?.message}")
                                Log.d("GlideException", "${e?.cause}")
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Log.d("GlideException", "$dataSource")
                                return false
                            }

                        })
                        .into(imageLecture)

                }
            }
        }

    }

    fun setItems(items: List<GetLecturesResponseItem>?) {
        this.itemsList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllLecturesViewHolder {
        val itemBinding: ItemLayoutLecturesBinding = ItemLayoutLecturesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return AllLecturesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = itemsList?.size ?: 0

    override fun onBindViewHolder(holder: AllLecturesViewHolder, position: Int) {
        val currentLecture = itemsList?.get(position)
        holder.bind(currentLecture)
        holder.itemBinding.root.setOnClickListener {
            if (currentLecture != null)
                onItemClick?.invoke(currentLecture)
        }
    }
}









