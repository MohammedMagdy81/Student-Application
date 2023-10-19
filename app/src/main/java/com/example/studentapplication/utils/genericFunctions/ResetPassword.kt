package com.example.studentapplication.utils.genericFunctions

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.studentapplication.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch

fun Fragment.resetPasswordDialog(
    onSendEmailClick: (String) -> Unit,
    observeToPasswordValidation: (EditText) -> Unit
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
        val newPassword = etReset.text.toString().trim()
        onSendEmailClick(newPassword)
        observeToPasswordValidation(etReset)
    }
    btnCancel.setOnClickListener {
        dialog.dismiss()
    }
}


