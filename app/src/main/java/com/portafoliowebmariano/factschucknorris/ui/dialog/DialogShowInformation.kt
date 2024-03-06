package com.portafoliowebmariano.factschucknorris.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.portafoliowebmariano.factschucknorris.databinding.DialogInformationBinding

object DialogShowInformation {
    fun showDialogInformation(context: Context){
        val inflater = LayoutInflater.from(context)
        val binding = DialogInformationBinding.inflate(inflater)

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setView(binding.root)

        alertDialog.show()
    }
}