package com.example.studentapplication.base

import androidx.recyclerview.widget.DiffUtil

class SimpleDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val checkIfTheSameItems: (oldItem: T, newItem: T) -> Boolean

) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        checkIfTheSameItems(oldList[oldItemPosition], newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true
}