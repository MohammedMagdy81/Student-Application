package com.example.studentapplication.ui.fragments.home.homePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.databinding.ItemLayoutLecturesBinding
import com.squareup.picasso.LruCache
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

                    Picasso.get()
                        .load(it.pictureUrl)
                        .resize(700, 600)
                        .centerCrop()
                        .placeholder(R.drawable.lectures)
                        .error(R.drawable.ic_error2)
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









