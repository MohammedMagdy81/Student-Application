package com.example.studentapplication.ui.fragments.home.homePage

import com.example.studentapplication.R
import com.example.studentapplication.base.BaseAdapter
import com.example.studentapplication.base.BaseInteractionListener
import com.example.studentapplication.domin.model.Lecture

class LecturesAdapter(
    items: List<Lecture>, listener: LecturesInteractionListener
) : BaseAdapter<Lecture>(items, listener) {


    override val layoutId = R.layout.item_layout_lectures
}

interface LecturesInteractionListener : BaseInteractionListener