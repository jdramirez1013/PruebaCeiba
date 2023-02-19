package com.jdr.pruebaceiba.ui.dialog

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.jdr.pruebaceiba.databinding.LayoutLoadingBinding


class DialogLoading(private val activity: Activity) {

    private lateinit var binding: LayoutLoadingBinding
    private lateinit var alertDialog: AlertDialog

    fun show() {
        binding = LayoutLoadingBinding.inflate(activity.layoutInflater)
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()

    }

    fun dismiss() {
        if(::alertDialog.isInitialized) alertDialog.dismiss()
    }

}