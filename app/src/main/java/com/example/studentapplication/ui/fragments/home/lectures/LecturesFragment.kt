package com.example.studentapplication.ui.fragments.home.lectures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentapplication.R
import com.example.studentapplication.utils.ViewsUtils.showBottomNav

class LecturesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lectures, container, false)
    }

    override fun onStart() {
        super.onStart()
        showBottomNav()
    }


}