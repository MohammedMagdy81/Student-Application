package com.example.studentapplication.utils.ViewsUtils

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
fun setTextColor(vararg views: TextView) {
    views.forEach {
        it.setTextColor(Color.RED)
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

 fun Fragment.submitAnswersQuizDialog(onGo: () -> Unit) {
    val dialog = AlertDialog.Builder(requireContext()).create()
    val view = layoutInflater.inflate(R.layout.dialog_logout, null)

    val btnConfirm = view.findViewById<Button>(R.id.btnConfirmLogout)
    btnConfirm.text = getString(R.string.confirm)

    val btnCancel = view.findViewById<Button>(R.id.btnCancelLogout)
    val textView = view.findViewById<TextView>(R.id.tvLogoutFromApp)
    textView.text = getString(R.string.sure_submit_answers)
    dialog.setView(view)

    btnConfirm.setOnClickListener {
        onGo.invoke()
        dialog.dismiss()
    }
    btnCancel.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()

}