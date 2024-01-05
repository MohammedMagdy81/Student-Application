package com.example.studentapplication.utils.ViewsUtils

import android.view.View
import androidx.fragment.app.Fragment
import com.example.studentapplication.R
import com.example.studentapplication.ui.activities.HomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar

fun hideViews(vararg views:View){
    views.forEach {
        it.visibility = View.GONE
    }
}

fun showViews(vararg views:View){
    views.forEach {
        it.visibility = View.VISIBLE
    }
}

fun Fragment.showBottomNav() {
    val bottomNav =
        (activity as HomeActivity).findViewById<ChipNavigationBar>(R.id.bottom_navigation)
    bottomNav.visibility = View.VISIBLE
}

fun Fragment.hideBottomNav() {
    val bottomNav =
        (activity as HomeActivity).findViewById<ChipNavigationBar>(R.id.bottom_navigation)
    bottomNav.visibility = View.GONE
}