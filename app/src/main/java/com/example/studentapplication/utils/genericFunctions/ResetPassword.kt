package com.example.studentapplication.utils.genericFunctions

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.studentapplication.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.resetPasswordDialog(
    onSendEmailClick: (String) -> Unit
) {
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
    val view: View = layoutInflater.inflate(R.layout.reset_password_layout, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val etReset = view.findViewById<EditText>(R.id.etResetPassword)
    val btnCancel = view.findViewById<Button>(R.id.btnCancelResetPassword)
    val btnConfirm = view.findViewById<Button>(R.id.btnConfirmResetPassword)

    btnConfirm.setOnClickListener {
        val email = etReset.text.toString().trim()
        if (!email.isEmpty()) {
            onSendEmailClick(email)
            etReset.error = null
            dialog.dismiss()
        } else {
            etReset.error = "من فضلك قم بإدخال الايميل !"
        }

    }
    btnCancel.setOnClickListener {
        dialog.dismiss()
    }
}