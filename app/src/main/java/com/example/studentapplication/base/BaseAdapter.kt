package com.example.studentapplication.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface BaseInteractionListener

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private var listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {


    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemLectureViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items.get(position)
        when (holder) {
            is ItemLectureViewHolder -> {
                holder.binding.setVariable(BR.item, currentItem)
                holder.binding.setVariable(BR.listener, listener)
            }
        }
    }

    fun setItems(newItems: List<T>) {
        val diffResult =
            DiffUtil.calculateDiff(SimpleDiffUtil(items, newItems) { oldItem, newItem ->
                areItemsTheSame(oldItem, newItem)
            })
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = items.size

    fun getItems() = items

    open fun areItemsTheSame(oldItem: T?, newItem: T?): Boolean {
        return oldItem?.equals(newItem) == true
    }

    class ItemLectureViewHolder(itemBinding: ViewDataBinding) : BaseViewHolder(itemBinding)
    abstract class BaseViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}