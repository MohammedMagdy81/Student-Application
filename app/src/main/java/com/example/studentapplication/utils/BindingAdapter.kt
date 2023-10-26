package com.example.studentapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studentapplication.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T> setItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        (view.adapter as BaseAdapter<T>?)?.setItems(items)
    } else {
        (view.adapter as BaseAdapter<T>?)?.setItems(emptyList())
    }
}

@BindingAdapter(value = ["app:imageUrl"])
fun setImage(view: ImageView, url: String) {
    Glide.with(view).load(url).centerCrop().into(view)
}